package com.linw.demoentity.entity;

import com.linw.demoentity.base.entity.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * 
 *t_system_user
 */
@Entity
@Table(name = "t_system_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemUser extends BaseEntity {
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