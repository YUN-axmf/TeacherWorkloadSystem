# 基于Java Swing的教师工作量管理系统
- 计算每个老师在一个学期中所教课程的总工作量。
（教师单个教学任务的信息为：教师号、姓名、性别、职称、认教课程、班级、班级数目、理论课时、实验课时、单个教学任务总课时）
## 1．教师信息处理
#### (1) 输入教师授课教学信息。
包括教师号、姓名、性别、职称、认教课程、班级、班级数目、理论课时、实验课时。
#### (2) 插入（修改）教师授课教学信息
#### (3) 删除教师授课教学信息
#### (4) 浏览教师授课教学信息
## 2．教师工作量数据处理
#### (1) 计算单个教学任务总课时。计算原则如下表：
- 班级数目2时 单个任务教学总课是为1.5*(理论课时+实验课时）
- 班级数目3时 单个任务教学总课是为2*(理论课时+实验课时）
- 班级数目>=4时 单个任务教学总课是为2.5*(理论课时+实验课时）
#### (2) 计算一个教师一个学期总的教学工作量。总的教学工作量=所有单个教学任务总课时之和。
#### (3)教师数据查询：
## 3．教师综合信息输出
