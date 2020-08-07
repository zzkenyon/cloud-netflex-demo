package com.pd.order.dto;

import com.pd.exception.ValidException;
import com.pd.goods.dto.ItemDto;
import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/8/6 15:45
 */
@Data
public class OrderDto {
    @NotNull(message = "name不能为空")
    private String name;
    @NotNull(message = "tel不能为空")
    private String tel;
    private String userId;
    @NotEmpty(message = "商品列表为空")
    private List<ItemDto> items;

    public void validData(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder stringBuilder=new StringBuilder();
            for(ObjectError oe:bindingResult.getAllErrors()){
                stringBuilder.append(oe.getDefaultMessage()+"\n");
            }
            throw new ValidException(stringBuilder.toString());
        }
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", userId='" + userId + '\'' +
                ", items=" + items +
                '}';
    }
}
