package net.sppan.base.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
@TableName("tb_role")
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

	/**
	 * 角色id
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;

	/**
	 * 角色名称
	 */
	private String name;

	/**
	 * 角色key
	 */
	private String key;

	/**
	 * 角色状态,0：正常；1：删除
	 */
	private Integer status;

	/**
	 * 角色描述
	 */
	private String description;

	/**
	 * 创建时间
	 */
	@TableField(value="create_time")
	private Date createTime;

	/**
	 * 更新时间
	 */
	@TableField(value="update_time")
	private Date updateTime;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
