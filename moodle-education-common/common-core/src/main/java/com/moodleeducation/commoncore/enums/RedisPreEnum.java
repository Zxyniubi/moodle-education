package com.moodleeducation.commoncore.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RedisPreEnum {

    SYS_MSG_SEND("sys_msg_send_", "发送站内信-sys_msg_send_"), //

    SYS_MSG_SEND_NUM("sys_msg_send_num_", "发送站内信key数量-sys_msg_send_num_"), //

    SYS_MSG("sys_msg_", "站内信-sys_msg"), //

    ADMINI_MENU("admini_menu_", "管理员菜单-admini_menu_"); //

    private String code;

    private String desc;

}
