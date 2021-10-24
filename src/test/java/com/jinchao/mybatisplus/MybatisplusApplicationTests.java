package com.jinchao.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.jinchao.mybatisplus.dao.UserMapper;
import com.jinchao.mybatisplus.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisplusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    /*@Test
    public void select(){
        List<User> list = userMapper.selectList(null);
        Assert.assertEquals(5,list.size());
        list.forEach(System.out::println);
    }*/
    @Test
    public void insert(){
        User user = new User();
        user.setName("向东");
        user.setAge(35);
        user.setManagerId(2L);
        user.setCreateTime(LocalDateTime.now());
        user.setRemark("这是一个备注信息哦！");
        user.setRemark1("这是备注1");
        int i = userMapper.insert(user);
        System.out.println("影响记录数:"+i);
    }
    /*
      查询名字包含"向"并且年龄小于60的人
      real_name like "%向%" and age < 60
     */
    @Test
    public void selectByWrapper(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("real_name","向").lt("age",60);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /*
      查询名字包含"向"并且年龄大于等于30小于等于40并且email不为空
      real_name like "%向%" and age between 30 and 40 and email is not null
     */
    @Test
    public void selectByWrapper2(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("real_name","向").between("age",30,40).isNotNull("email");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /*
      查询名字为王姓或者年龄大于等于30，按照年龄降序排列，年龄相同按照id升序排列
      real_name like "王%" or age >= 30 order by age desc,id asc
     */
    @Test
    public void selectByWrapper3(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("real_name","王").or().ge("age",30).orderByDesc("age").orderByAsc("id");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /*
      查询创建日期为2021年9月25日并且直属上级为王姓的
      date_format(create_time,'%Y-%m-%d') and manager_id in (select id from user where real_name like '王%')
     */
    @Test
    public void selectByWrapper4(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d') = {0}","2021-09-25")
                .inSql("manager_id","select id from mp_user where real_name like '王%'");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /*
      查询名字为向姓并且(年龄小于40或邮箱不为空)
     */
    @Test
    public void selectByWrapper5(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("real_name", "向").and(qw -> qw.lt("age", 40)
                .or().isNotNull("email"));
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /*
      查询名字为向姓或者（年龄小于40并且年龄大于20并且邮箱不为空）
      name like '向%' or (age < 40 and age > 20 and email is not null)
     */
    @Test
    public void selectByWrapper6(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("real_name","向").or(qw -> qw.lt("age",40)
                .gt("age",20).isNotNull("email"));
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /*
      查询（年龄小于40或邮箱不为空）并且名字为向姓的
      (age < 40 or email is not null) and name like '王%'
     */
    @Test
    public void selectByWrapper7(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.nested(qw -> qw.lt("age",40).or().isNotNull("email"))
                .likeRight("real_name","向");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /*
      查询年龄为18,19,20，64的
      age in (18,19,20，64)
     */
    @Test
    public void selectByWrapper8(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList(18,19,20,64));
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /*
      只返回满足条件的其中一条语句即可
      limit 1
     */
    @Test
    public void selectByWrapper9(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList(18,19,20,64)).last("limit 1");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /*
      select不列出全部字段
      查询名字包含"向"并且年龄小于60的人
      real_name like "%向%" and age < 60
     */
    @Test
    public void selectByWrapperSupper(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","real_name").like("real_name","向").lt("age",60);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /*
      select不列出全部字段（字段较多时用排除法）
      查询名字包含"向"并且年龄小于60的人
      real_name like "%向%" and age < 60
     */
    @Test
    public void selectByWrapperSupper2(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(User.class,info -> !info.getColumn().equals("create_time") && !info.getColumn().equals("manager_id"))
                .like("real_name","向").lt("age",60);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /*
      select不列出全部字段（字段较多时用排除法）
      查询名字包含"向"并且年龄小于60的人
      real_name like "%向%" and age < 60
     */
    @Test
    public void testCondition(){
        String name = "";
        String email = "s";
        condition(name,email);
    }

    private void condition(String name,String email){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        /*if(StringUtils.isNotEmpty(name)){
            queryWrapper.like("real_name",name);
        }
        if(StringUtils.isNotEmpty(email)){
            queryWrapper.like("email",email);
        }*/
        queryWrapper.like(StringUtils.isNotEmpty(name),"real_name",name).
                like(StringUtils.isNotEmpty(email),"email",email);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /*
      实体作为条件构造器构造方法的参数
     */
    @Test
    public void selectByWrapperEntity(){
        User whereUser = new User();
        whereUser.setName("向北");
        whereUser.setAge(35);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(whereUser);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /*
      AllEq用法
     */
    @Test
    public void selectByWrapperAllEq(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Map<String,Object> params = new HashMap<>();
        params.put("real_name","王大锤");
        params.put("age",null);
//        queryWrapper.allEq(params,false);
        //过滤
        queryWrapper.allEq((k,v) -> !k.equals("real_name"),params);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /*
      Maps
     */
    @Test
    public void selectByWrapperMaps(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","real_name").like("real_name","向").lt("age",60);
        List<Map<String, Object>> userList = userMapper.selectMaps(queryWrapper);
        userList.forEach(System.out::println);
    }

    /*
     按照直属上级分组，查询每组的平均年龄、最大年龄、最小年龄。
     并且只取年龄总和小于500的组。
     select avg(age) avg_age, min(age) min_age, max(age) max_age
     from user
     group by manager_id
     having sum(age) < 500
    */
    @Test
    public void selectByWrapperMaps2(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("avg(age) avg_age","min(age) min_age","max(age) max_age").groupBy("manager_id")
                    .having("sum(age) < {0}",500);
        List<Map<String, Object>> userList = userMapper.selectMaps(queryWrapper);
        userList.forEach(System.out::println);
    }

    /*
      selectObjs
     */
    @Test
    public void selectByWrapperObjs(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","real_name").like("real_name","向").lt("age",60);
        List<Object> userList = userMapper.selectObjs(queryWrapper);
        userList.forEach(System.out::println);
    }

    /*
      selectCount
     */
    @Test
    public void selectByWrapperCount(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("real_name","向").lt("age",60);
        Integer count = userMapper.selectCount(queryWrapper);
        System.out.println("总记录数："+count);
    }

    /*
      selectOne
     */
    @Test
    public void selectByWrapperOne(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("real_name","向北").lt("age",40);
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    /*
      Lambda
     */
    @Test
    public void selectByWrapperLambda(){
//        LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();
//        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();
        lambdaQuery.like(User::getName,"向").lt(User::getAge,40);
        List<User> userList = userMapper.selectList(lambdaQuery);
        userList.forEach(System.out::println);
    }

    /*
      Lambda2
     */
    @Test
    public void selectByWrapperLambda2(){
        List<User> userList = new LambdaQueryChainWrapper<User>(userMapper).like(User::getName, "向")
                    .ge(User::getAge, 40).list();
        userList.forEach(System.out::println);
    }

    /*
      分页
     */
    @Test
    public void selectPage(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.ge("age",19);
        Page<User> page = new Page<User>(1,2,false);
        IPage<User> iPage = userMapper.selectPage(page, queryWrapper);
        System.out.println("总页数:"+iPage.getPages());
        System.out.println("总记录数:"+iPage.getTotal());
        List<User> userList = iPage.getRecords();
        userList.forEach(System.out::println);
    }

    /*
      分页
     */
    @Test
    public void selectMapsPage(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.ge("age",19);
        Page<User> page = new Page<User>(1,2);
        IPage<Map<String, Object>> iPage = userMapper.selectMapsPage(page, queryWrapper);
        System.out.println("总页数:"+iPage.getPages());
        System.out.println("总记录数:"+iPage.getTotal());
        List<Map<String, Object>> userList = iPage.getRecords();
        userList.forEach(System.out::println);
    }

}
