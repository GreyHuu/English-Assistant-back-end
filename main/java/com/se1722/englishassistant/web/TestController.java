package com.se1722.englishassistant.web;

import com.se1722.englishassistant.entity.ADemoUser;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//@RestController
// 配置应设在/users下
@RequestMapping("/demo-users")
public class TestController {
    // 创建线程安全的Map，模拟users信息的存储
    static Map<Long, ADemoUser> users = Collections.synchronizedMap(new HashMap<Long, ADemoUser>());
    /**
     * 获得全部的用户
     *
     * @return
     */
    @GetMapping("/get-users")
    public List<ADemoUser> getUserList() {
        List<ADemoUser> ADemoUserList = new ArrayList<ADemoUser>(users.values());
        return ADemoUserList;
    }

    /**
     * @param ADemoUser
     * @return
     */
    @PostMapping("/add-user")
    public String addUser(@RequestBody ADemoUser ADemoUser) {
//        RequestBody 用例绑定post中json格式的data
        users.put(ADemoUser.getId(), ADemoUser);
        return "success";
    }

    /**
     * 通过/users/id的格式获得某个用户
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ADemoUser getUserById(@PathVariable Long id) {
//        PathVariable可以获得url上的预定的值
        return users.get(id);
    }

    /**
     * 处理/user/id的put请求   根据id来对user进行信息的更新
     *
     * @param id
     * @param ADemoUser
     * @return
     */
    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody ADemoUser ADemoUser) {
        ADemoUser u = users.get(id);
        u.setName(ADemoUser.getName());
        u.setAge(ADemoUser.getAge());
        users.put(id, u);
        return "success";
    }

    /**
     * 处理删除用户的请求
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }
}
