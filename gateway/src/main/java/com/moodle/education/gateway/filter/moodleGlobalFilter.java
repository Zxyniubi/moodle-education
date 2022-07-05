package com.moodle.education.gateway.filter;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.moodleeducation.commoncore.base.BaseException;
import com.moodleeducation.commoncore.enums.RedisPreEnum;
import com.moodleeducation.commoncore.enums.ResultEnum;
import com.moodleeducation.commoncore.tools.JWTUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Zxy
 */

@Component
public class moodleGlobalFilter implements GlobalFilter, Ordered {
    private final String TOKEN = "token";
    private static final String USERNO = "userNo";
    private static final Logger log = LoggerFactory.getLogger(moodleGlobalFilter.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
         ServerHttpRequest request = exchange.getRequest();
         String uri = request.getPath().value();

        if (uri.contains("/fallback")) {
            //回退接口不鉴权
            return chain.filter(exchange);
        }
        if(uri.contains("/api/common")){
            //公共接口，不鉴权
            return chain.filter(exchange);
        }
        if(uri.contains("/api/user")){
            return chain.filter(exchange);
        }
          Integer userNo = getUserNoByToken(request);
        if(uri.contains("/api/admin")){
            if(!stringRedisTemplate.opsForValue().getOperations().hasKey(RedisPreEnum.ADMINI_MENU.getCode().concat(userNo.toString()))){
                throw new BaseException(ResultEnum.TOKEN_PAST);
            }
        }
        return chain.filter(exchange);
//        return request(exchange,chain,modifiedBody(exchange,userNo));
    }

    /**
     * 优先级，order越大，优先级越低
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }


    private Mono<String> modifiedBody(ServerWebExchange serverWebExchange, Integer userNo) {
        MediaType mediaType = serverWebExchange.getRequest().getHeaders().getContentType();
        ServerRequest serverRequest = ServerRequest.create(serverWebExchange, HandlerStrategies.withDefaults().messageReaders());
        return serverRequest.bodyToMono(String.class).flatMap(body -> {
            JSONObject bodyJson;
            if (MediaType.APPLICATION_FORM_URLENCODED.isCompatibleWith(mediaType)) {
                Map<String, Object> bodyMap = decodeBody(body);
                bodyJson = JSONUtil.parseObj(bodyMap);
            } else {
                bodyJson = JSONUtil.parseObj(body);
            }
            if (ObjectUtil.isNotNull(userNo)) {
                bodyJson.set(USERNO, userNo);
            }
            return Mono.just(JSONUtil.toJsonStr(bodyJson));
        });
    }

    private Map<String, Object> decodeBody(String body) {
        return Arrays.stream(body.split("&")).map(s -> s.split("=")).collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));
    }

    private Mono<Void> request(ServerWebExchange exchange, GatewayFilterChain chain, Mono<String> modifiedBody) {
        BodyInserter bodyInserter = BodyInserters.fromPublisher(modifiedBody, String.class);
        HttpHeaders headers = new HttpHeaders();
        headers.putAll(exchange.getRequest().getHeaders());
        headers.remove(HttpHeaders.CONTENT_LENGTH);
        CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);
        return bodyInserter.insert(outputMessage, new BodyInserterContext())
                .then(Mono.defer(() -> {
                    ServerHttpRequestDecorator decorator =
                            new ServerHttpRequestDecorator(exchange.getRequest()) {
                                @Override
                                public HttpHeaders getHeaders() {
                                    return headers;
                                }

                                @Override
                                public Flux<DataBuffer> getBody() {
                                    return outputMessage.getBody();
                                }
                            };
                    return chain.filter(exchange.mutate().request(decorator).build());
                }));
    }
    private Integer getUserNoByToken(ServerHttpRequest request){
        String token = request.getHeaders().getFirst(TOKEN);
        if (StringUtils.isEmpty(token)) {
            throw new BaseException("Token为空");
        }
        DecodedJWT jwt = null;
        try {
            jwt = JWTUtils.verify(token);
        }catch (Exception e){
            log.error("token异常，token={}",token.toString());
            throw new BaseException(ResultEnum.TOKEN_ERROR);
        }
        if(jwt==null){
            throw new BaseException(ResultEnum.TOKEN_ERROR);
        }
         Integer userNo = JWTUtils.getUserNo(jwt);
        if (userNo <= 0){
            throw new BaseException(ResultEnum.TOKEN_ERROR);
        }
        stringRedisTemplate.opsForValue().set(userNo.toString(),token,1, TimeUnit.HOURS);
        return userNo;
    }
}
