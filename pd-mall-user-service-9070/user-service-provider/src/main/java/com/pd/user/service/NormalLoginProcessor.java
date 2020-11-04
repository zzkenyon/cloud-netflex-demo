package com.pd.user.service;

import com.pd.exception.BizException;
import com.pd.exception.ValidException;
import com.pd.user.dto.AuthLoginDto;
import com.pd.user.enums.LoginTypeEnum;
import com.pd.user.mapper.entity.MemberPo;
import com.pd.user.mapper.entity.MemberPoExample;
import com.pd.user.mapper.persistence.MemberPoMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/11/4 10:07
 */
@Service
@Slf4j
public class NormalLoginProcessor extends AbstractLogin{
    private MemberPoMapper memberPoMapper;
    @Autowired
    public void setMemberPoMapper(MemberPoMapper memberPoMapper) {
        this.memberPoMapper = memberPoMapper;
    }

    @Override
    protected Integer getLoginType() {
        return LoginTypeEnum.NORMAL.getCode();
    }

    @Override
    public void validate(AuthLoginDto authLoginDto) {
        if(StringUtils.isBlank(authLoginDto.getUsername())||StringUtils.isBlank(authLoginDto.getPassword())){
            throw new ValidException("帐号或者密码不能为空");
        }
    }

    @Override
    public MemberPo doProcessor(AuthLoginDto authLoginDto) {
        log.info("begin NormalLoginProcessor.doProcess: " + authLoginDto);
        MemberPoExample memberPoExample = new MemberPoExample();
        memberPoExample.createCriteria().andStateEqualTo(1).andUsernameEqualTo(authLoginDto.getUsername());
        List<MemberPo> members = memberPoMapper.selectByExample(memberPoExample);
        if(members == null || members.size()==0){
            throw new BizException("用户名密码错误");
        }
        if(!DigestUtils.md5DigestAsHex(authLoginDto.getPassword().getBytes()).equals(members.get(0).getPassword())){
            throw new BizException("用户名密码错误");
        }
        return members.get(0);
    }
}
