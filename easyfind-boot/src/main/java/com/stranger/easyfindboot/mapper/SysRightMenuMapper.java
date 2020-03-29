package com.stranger.easyfindboot.mapper;

import com.stranger.easyfindboot.entity.SysRightMenu;
import com.stranger.easyfindboot.entity.SysRightMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRightMenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_right_menu
     *
     * @mbg.generated Thu Mar 19 08:59:25 CST 2020
     */
    long countByExample(SysRightMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_right_menu
     *
     * @mbg.generated Thu Mar 19 08:59:25 CST 2020
     */
    int deleteByExample(SysRightMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_right_menu
     *
     * @mbg.generated Thu Mar 19 08:59:25 CST 2020
     */
    int insert(SysRightMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_right_menu
     *
     * @mbg.generated Thu Mar 19 08:59:25 CST 2020
     */
    int insertSelective(SysRightMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_right_menu
     *
     * @mbg.generated Thu Mar 19 08:59:25 CST 2020
     */
    List<SysRightMenu> selectByExample(SysRightMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_right_menu
     *
     * @mbg.generated Thu Mar 19 08:59:25 CST 2020
     */
    int updateByExampleSelective(@Param("record") SysRightMenu record, @Param("example") SysRightMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_right_menu
     *
     * @mbg.generated Thu Mar 19 08:59:25 CST 2020
     */
    int updateByExample(@Param("record") SysRightMenu record, @Param("example") SysRightMenuExample example);
}