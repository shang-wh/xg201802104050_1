package cn.edu.sdjzu.xg.bysj.controller.basic;//package cn.edu.sdjzu.xg.bysj.controller.basic;
//
//import cn.edu.sdjzu.xg.bysj.domain.User;
//import cn.edu.sdjzu.xg.bysj.service.UserService;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import util.JSONUtil;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.Collection;
//
//public class UserController extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        //根据request对象，获得代表参数的JSON字串
//        String user_json = JSONUtil.getJSON(request);
//        //将JSON字串解析为User对象
//        User userToAdd = JSON.parseObject(user_json, User.class);
//        //前台没有为id赋值，此处模拟自动生成id。Dao能实现数据库操作时，应删除此语句。
//        userToAdd.setId(4 + (int)(Math.random()*100));
//        //响应
//        //创建JSON对象message，以便往前端响应信息
//        JSONObject message = new JSONObject();
//        try {
//            //增加User对象
//            UserService.getInstance().addUser(userToAdd);
//            //加入数据信息
//            message.put("statusCode", "200");
//            message.put("message", "增加成功");
//        } catch(Exception e){
//            message.put("message", "网络异常");
//            e.printStackTrace();
//        }
//        //响应message到前端
//        response.getWriter().println(message);
//    }
//
//    /**
//     * DELETE, http://localhost:8888/user.ctl?id=1
//     * 删除一个教师对象：根据来自前端请求的id，删除数据库表中id的对应记录
//     * @param request
//     * @param response
//     * @throws ServletException
//     * @throws IOException
//     */
//    @Override
//    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //读取参数id
//        String id_str = request.getParameter("id");
//        int id = Integer.parseInt(id_str);
//        //创建JSON对象message，以便往前端响应信息
//        JSONObject message = new JSONObject();
//        //到数据库表中删除对应的教师
//        try {
//            UserService.getInstance().deleteUser(id);
//            //加入数据信息
//            message.put("statusCode", "200");
//            message.put("message", "删除成功");
//        } catch(Exception e){
//            message.put("message", "网络异常");
//            e.printStackTrace();
//        }
//        //响应message到前端
//        response.getWriter().println(message);
//    }
//    /**
//     * PUT, http://localhost:8888/user.ctl
//     * 修改一个教师对象：将来自前端请求的JSON对象，更新数据库表中相同id的记录
//     * @param request
//     * @param response
//     * @throws ServletException
//     * @throws IOException
//     */
//    @Override
//    protected void doPut(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String profTitle_json = JSONUtil.getJSON(request);
//        //将JSON字串解析为教师对象
//        User userToAdd = JSON.parseObject(profTitle_json, User.class);
//        //创建JSON对象message，以便往前端响应信息
//        JSONObject message = new JSONObject();
//        //增加User对象
//        try {
//            UserService.getInstance().updateUser(userToAdd);
//            //加入数据信息
//            message.put("statusCode", "200");
//            message.put("message", "更新成功");
//        } catch(Exception e){
//            message.put("message", "网络异常");
//            e.printStackTrace();
//        }
//        //响应message到前端
//        response.getWriter().println(message);
//    }
//    /**
//     * catch (SQLException e) {
//     *             message.put("message", "数据库操作异常");
//     *             e.printStackTrace();
//     *         }catch(Exception e){
//     *             message.put("message", "网络异常");
//     *             e.printStackTrace();
//     *         }
//     *         //响应message到前端
//     *         response.getWriter().println(message);
//     *     }
//     */
//
//    /**
//     * GET, http://localhost:8888/user.ctl?id=1, 查询id=1的教师
//     * GET, http://localhost:8888/user.ctl, 查询所有的教师
//     * 响应一个或所有教师对象
//     * @param request
//     * @param response
//     * @throws ServletException
//     * @throws IOException
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        //读取参数id
//        String id_str = request.getParameter("id");
//        //创建JSON对象message，以便往前端响应信息
//        JSONObject message = new JSONObject();
//        try {
//            //如果id = null, 表示响应所有学院对象，否则响应id指定的学院对象
//            if (id_str == null) {
//                responseUsers(response);
//            } else {
//                int id = Integer.parseInt(id_str);
//                responseUser(id, response);
//            }
//        }catch (SQLException e){
//            message.put("message", "数据库操作异常");
//            e.printStackTrace();
//            //响应message到前端
//            response.getWriter().println(message);
//        }catch(Exception e){
//            message.put("message", "网络异常");
//            e.printStackTrace();
//            //响应message到前端
//            response.getWriter().println(message);
//        }
//    }
//    //响应一个教师对象
//    private void responseUser(int id, HttpServletResponse response)
//            throws ServletException, IOException, SQLException {
//        //根据id查找教师
//        User user = UserService.getInstance().find(id);
//        String user_json = JSON.toJSONString(user);
//        //响应message到前端
//        response.getWriter().println(user_json);
//    }
////    //响应一个教师对象
////    private void responseUser(String username, HttpServletResponse response)
////            throws ServletException, IOException, SQLException {
////        //根据姓名查找教师
////        User user = UserService.getInstance().findUserByUsername(username);
////        String user_json = JSON.toJSONString(user);
////        //响应message到前端
////        response.getWriter().println(user_json);
////    }
//    //响应所有教师对象
//    private void responseUsers(HttpServletResponse response)
//            throws ServletException, IOException, SQLException {
//        //获得所有教师
//        Collection<User> users = UserService.getInstance().findAll();
//        String users_json = JSON.toJSONString(users, SerializerFeature.DisableCircularReferenceDetect);
//        //响应message到前端
//        response.getWriter().println(users_json);
//    }
//}
