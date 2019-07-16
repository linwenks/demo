package com.linw.demoentity.bo;

import com.linw.demoentity.base.bo.BaseBO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * 
t_system_user
 *
 */
@Entity
@Table(name = "t_system_user")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class SystemUser extends BaseBO {
    /*
     * 
     */
    @Id
    private Long id;

    /*
     * 
     */
    private String loginName;

    /*
     * 
     */
    private String loginPwd;

    /*
     * 是否删除(0:是 1:否)
     */
    private Integer delete;

    /*
     * 
     */
    private Long createTime;

    /*
     * 
     */
    private Long updateTime;

    /*
     * 
     */
    private Integer version;
}