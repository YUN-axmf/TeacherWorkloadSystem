package list;

/**
 * 教师列表类
 *
 * 成员变量：
 * 教师信息、表长
 *
 * 方法：
 * 插入信息、修改信息、删除信息、显示信息、查询信息
 */
public class TeacherList
{
    // 测试方法，仅供调试代码使用
    public static void main(String[] args)
    {

    }

    private Teacher[] teachers;
    private int curLen;

    public TeacherList(int maxSize)
    {
        teachers = new Teacher[maxSize];
        curLen = 0;
    }

    public void clear()
    {
        curLen = 0;
    }

    public boolean isEmpty()
    {
        return curLen == 0;
    }

    public int length()
    {
        return curLen;
    }

    // 按教工号升序顺序插入数据
    public void insert(Teacher t)
    {
        teachers[curLen] = t;
        curLen++;
        sort();
    }

    //由教工号获取教师在列表中的序号
    public int indexOf(int num)
    {
        int index = -1;         // -1表示无相关数据
        for (int i = 0; i < curLen; i++)
        {
            if (num == teachers[i].getNumber())
            {
                index = i;
                break;
            }
        }
        return index;
    }

    // 按教工号删除数据
    public void remove(int num)
    {
        int i = indexOf(num);
        for(int j = i; j < curLen - 1; j++)
        {
            teachers[j] = teachers[j + 1];
        }
        curLen--;
    }

    //按教工号获取教师信息
    public Teacher get(int num)
    {
        for (int i = 0; i < curLen; i++)
        {
            if (num == teachers[i].getNumber())
                return teachers[i];
        }
        return null;    // 无相关信息时返回空值
    }

    //按序号获取教师信息
    public Teacher getByIndex(int index)
    {
        return teachers[index];
    }

    // 按教师号对列表升序排列
    public void sort()
    {
        for (int i = 0; i < curLen - 1; i++)
        {
            int minIndex = i;
            for (int j = i + 1; j < curLen; j++)
            {
                if (teachers[minIndex].getNumber() > teachers[j].getNumber())
                    minIndex = j;
            }
            Teacher temp = teachers[minIndex];
            teachers[minIndex] = teachers[i];
            teachers[i] = temp;
        }
    }

    /*// 显示列表
    public void display()
    {
        System.out.println("教师信息如下:");
        for (int i = 0; i < curLen; i++)
            teachers[i].printTeacherInformation();
    }*/

    public int getCurLen()
    {
        return curLen;
    }

    public Teacher[] getTeachers()
    {
        return teachers;
    }

    public void setCurLen(int curLen)
    {
        this.curLen = curLen;
        for (int i = 0; i < curLen; i++)
        {
            teachers[i] = new Teacher();
        }
    }

    public void setTeachers(Teacher[] teachers)
    {
        this.teachers = teachers;
    }
}