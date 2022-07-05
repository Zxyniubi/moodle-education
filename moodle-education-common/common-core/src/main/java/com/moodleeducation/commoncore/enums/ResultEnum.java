package com.moodleeducation.commoncore.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {
    //成功
    SUCCESS(200,"成功"),
    // token异常
    TOKEN_PAST(301, "token过期"), TOKEN_ERROR(302, "token异常"),
    // 登录异常
    LOGIN_ERROR(303, "登录异常"), REMOTE_ERROR(304, "异地登录"),MENU_PAST(305, "菜单过期"), MENU_NO(306, "没此权限，请联系管理员！"),

    // 课程异常，4开头
    QUIZ_SAVE_FAIL(403, "添加失败"), QUIZ_UPDATE_FAIL(404, "更新失败"), QUIZ_DELETE_FAIL(405, "删除失败"),
    //
    COLLECTION(406, "已收藏"), USER_ADVICE(406, "保存建议失败,不能重复提建议"), VIDEO_AUDIT_FAIL(407, "审核失败"),

    // 审核异常，5开头
    COURSE_AUDIT_FAIL_ALREADY(501, "该视频已审核且未通过"), COURSE_AUDIT_SUCCESS_ALREADY(502, "视频已审核通过，无需在审核"), COURSE_AUDIT_FAIL(503, "审核失败，请稍后再试"),
    //
    USER_SAVE_FAIL(504, "添加失败"), USER_UPDATE_FAIL(505, "更新失败"), LECTURER_REQUISITION_FAIL(506, "申请失败！该账号已提交申请入驻成为讲师，审核不通过，请联系平台管理员"), USER_LECTURER_AUDIT(507, "审核失败"), USER_SEND_FAIL(508, "发送失败"),
    USER_DELETE_FAIL(509, "删除失败"),

    // 系統异常，6开头
    SYSTEM_SAVE_FAIL(601, "添加失败"), SYSTEM_UPDATE_FAIL(602, "更新失败"), SYSTEM_DELETE_FAIL(603, "删除失败"),

    // 错误
    ERROR(999, "错误");
    private  Integer code;
    private  String desc;
}
