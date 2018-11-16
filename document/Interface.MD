#### 写在前面

---
此文档用于描述接口，部分更新不及时或不准确联系我
Email: yiranwenchengzhi@163.com


---
## 接口文档
1. /crawl/init/test
参数: 无
请求类型: GET
说明: 测试使用
返回值: ResponseBean 状态100，成功(以下用Success代替)

2./crawl/task/insert
参数: page=>c5页面编号;from=>来源，默认C5即可
请求类型: GET
说明: 增加需要关注的饰品
返回值: ResponseBean.Success

3./crawl/jewelry/count
参数: 无
请求类型: GET
说明: 查询饰品列表总数
返回值: int数字

4./crawl/jewelry/queryAll
参数: 无
请求类型: GET
说明: 查询饰品列表
返回值: 饰品列表
