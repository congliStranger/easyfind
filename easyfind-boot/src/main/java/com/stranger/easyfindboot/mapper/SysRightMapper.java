package com.stranger.easyfindboot.mapper;

import com.stranger.easyfindboot.entity.SysRight;
import com.stranger.easyfindboot.entity.SysRightExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRightMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_right
     *
     * @mbg.generated Thu Mar 19 08:59:25 CST 2020
     */
    long countByExample(SysRightExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_right
     *
     * @mbg.generated Thu Mar 19 08:59:25 CST 2020
     */
    int deleteByExample(SysRightExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_right
     *
     * @mbg.generated Thu Mar 19 08:59:25 CST 2020
     */
    int insert(SysRight record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_right
     *
     * @mbg.generated Thu Mar 19 08:59:25 CST 2020
     */
    int insertSelective(SysRight record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_right
     *
     * @mbg.generated Thu Mar 19 08:59:25 CST 2020
     */
    List<SysRight> selectByExample(SysRightExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_right
     *
     * @mbg.generated Thu Mar 19 08:59:25 CST 2020
     */
    int updateByExampleSelective(@Param("record") SysRight record, @Param("example") SysRightExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_right
     *
     * @mbg.generated Thu Mar 19 08:59:25 CST 2020
     */
    int updateByExample(@Param("record") SysRight record, @Param("example") SysRightExample example);
}