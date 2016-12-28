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
 * 用户账户表
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
@TableName("tb_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;

	/**
	 * 账户名
	 */
	@TableField(value="user_name")
	private String userName;

	/**
	 * 昵称
	 */
	@TableField(value="nick_name")
	private String nickName;

	/**
	 * 用户密码
	 */
	private String password;

	/**
	 * 性别
	 */
	private Integer sex;

	/**
	 * 出生日期
	 */
	private Date birthday;

	/**
	 * 电话
	 */
	private String telephone;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 住址
	 */
	private String address;

	/**
	 * 逻辑删除状态
	 */
	@TableField(value="delete_status")
	private Integer deleteStatus;

	/**
	 * 是否锁定
	 */
	private Integer locked;

	/**
	 * 用户描述
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
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
