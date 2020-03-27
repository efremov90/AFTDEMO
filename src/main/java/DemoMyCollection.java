package main.java;

import java.util.Stack;

//Элемент списка
class MyElmList {
    private Object Data;
    private MyElmList PrevElmList, NextElmList;

    public MyElmList(Object data, MyElmList prevElmList, MyElmList nextElmList) {
        Data = data;
        PrevElmList = prevElmList;
        NextElmList = nextElmList;
    }

    public Object getData() {
        return Data;
    }

    public MyElmList getPrevElmList() {
        return PrevElmList;
    }

    public MyElmList getNextElmList() {
        return NextElmList;
    }

    public void setPrevElmList(MyElmList prevElmList) {
        PrevElmList = prevElmList;
    }

    public void setNextElmList(MyElmList nextElmList) {
        NextElmList = nextElmList;
    }
}

//Список
class MyList {
    private int count=0; //количество элементов
    private MyElmList firstElm,endElm; //первый и последний элемент списка

    public MyList(Object data) {
        this.firstElm = new MyElmList(data,null,null);
        this.endElm = this.firstElm;
        this.count = 1;
    }

    public int getCount() {
        return count;
    }

    //вспомогательный метод для get
    private MyElmList getMyElmList(int index) {
        MyElmList cur=firstElm;
        for (int i = 1; i <= count; i++) {
            if (i==index) {
                return cur;
            } else {
                cur=cur.getNextElmList();
            };
        }
        return null;
    }

    //получение элемента по индексу
    Object get(int index) {
        MyElmList cur=firstElm;
        for (int i = 1; i <= count; i++) {
            if (i==index) {
                return cur.getData();
            } else {
                cur=cur.getNextElmList();
            };
        }
        return null;
    }

    //добавление элемента в список
    void add(int index, Object data) {
        try {
            if (index>count+1) {
                throw new IllegalArgumentException("Позиция вставки "+index+" превышает допустимое значение "+(count+1));
            }
            if (index<1) {
                throw new IllegalArgumentException("Позиция вставки "+index+" меньше минимальной 1");
            }
            //вставка в начало
            if (index==1) {
                MyElmList temp = firstElm;
                firstElm = new MyElmList(data,null,temp);
                temp.setPrevElmList(firstElm);
            }
            //вставка в конец
            if (index==count+1) {
                MyElmList temp = endElm;
                endElm = new MyElmList(data, temp, null);
                temp.setNextElmList(endElm);
            }
            //вставка в середину (текущий cur сдвигается вперед next)
            if ((index>1)&&(index<count+1)) {
                MyElmList tempCur = getMyElmList(index);
                MyElmList tempPrev = tempCur.getPrevElmList();
                MyElmList tempNew = new MyElmList(data, tempPrev,tempCur);
                tempPrev.setNextElmList(tempNew);
                tempCur.setPrevElmList(tempNew);
            }
            //увеличиваем количество элементов
            count=count+1;
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        MyElmList cur=firstElm;
        String str="MyList{";
        for (int i = 1; i <= count; i++) {
            str=str+"{"+cur.getData().toString()+"}";
            //после последнего элемента запятую ставить не нужно
            if (i<count) {
                str=str+",";
            }
            cur=cur.getNextElmList();
        }
        str=str+"}";
        return str;
    }
}

class MyNode {
    private int Key;
    private int Level;
    private Object Data;
    private MyNode LeftNode, RightNode;

    public MyNode(int key, Object data, MyNode leftNode, MyNode rightNode) {
        Key = key;
        Data = data;
        LeftNode = leftNode;
        RightNode = rightNode;
    }

    public int getKey() {
        return Key;
    }

    public int getLevel() {
        return Level;
    }

    public Object getData() {
        return Data;
    }

    public MyNode getLeftNode() {
        return LeftNode;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public void setLeftNode(MyNode leftNode) {
        LeftNode = leftNode;
    }

    public MyNode getRightNode() {
        return RightNode;
    }

    public void setRightNode(MyNode rightNode) {
        RightNode = rightNode;
    }
}

class MyTree {
    private int Count=0;
    private MyNode Head;

    public MyTree(int key, Object data) {
        Head = new MyNode(key, data, null, null);
        Count=1;
    }

    public int getCount() {
        return Count;
    }

    public Object get(int key) {
        MyNode cur = Head;
        while ((cur.getLeftNode()!=null)&&(cur.getKey()>key)
        ||((cur.getRightNode()!=null)&&(cur.getKey()<=key))) {
            if ((cur.getLeftNode()!=null)&&(cur.getKey()>key)) {
                cur=cur.getLeftNode();
            }
            if ((cur.getRightNode()!=null)&&(cur.getKey()<=key)) {
                cur=cur.getRightNode();
            }
        }
        if (cur.getKey()==key) {
            return cur.getData();
        } else {
            return  null;
        }
    }

    public void add(int key, Object data) {
        MyNode cur = Head;
        while ((cur.getLeftNode()!=null)&&(cur.getKey()>key)
                ||((cur.getRightNode()!=null)&&(cur.getKey()<=key))) {
            if ((cur.getLeftNode()!=null)&&(cur.getKey()>key)) {
                cur=cur.getLeftNode();
            }
            if ((cur.getRightNode()!=null)&&(cur.getKey()<=key)) {
                cur=cur.getRightNode();
            }
        }
        MyNode tempNew = new MyNode(key, data, null, null);
        Count=Count+1;
        if (cur.getKey()<=key) {
            cur.setRightNode(tempNew);
        } else {
            cur.setLeftNode(tempNew);
        }
    }

    //Вертикальный обратный обход https://habr.com/ru/post/144850/
    public String toString() {
        String str="MyTree{";
        //дополнительно выводится информация об уровне
        //уровень вычисляется при проходе
        int currentLevel=0;
        Stack<MyNode> stack = new Stack<>();
        MyNode cur = Head;
        while ((cur!=null)||(!stack.empty())) {
            if (!stack.empty()) {
                //Обрабатываем верхний узел из стека.
                cur=stack.pop();
                currentLevel=cur.getLevel();
                //Если в текущем узле имеется правое поддерево, начинаем следующую итерацию с правого узла.
                //Если правого узла нет, пропускаем шаг со спуском и переходим к обработке следующего узла из стека.
                if (cur.getRightNode()!=null) {
                    cur=cur.getRightNode();
                    currentLevel=currentLevel+1;
                } else {
                    cur=null;
                }
            }
            //из текущего узла «спускаемся» до самого нижнего левого узла, добавляя в стек все посещенные узлы
            while (cur!=null) {
                //вычисляется терминальный узел
                boolean terminate;
                if ((cur.getLeftNode()==null)&&(cur.getRightNode()==null)) terminate=true;
                else terminate=false;

                str=str+"{key="+cur.getKey()+
                        ",level="+(currentLevel)+
                        ",terminate="+terminate+
                        ",data={"+cur.getData().toString()+"}}";
                if (!str.equals("MyTree{")) {
                    str = str + ",";
                }
                //при проходе уровень вычисляется и сохраняется у узла для того,
                //чтобы рассчитать относительно него уровень последующих узлов
                cur.setLevel(currentLevel);
                stack.push(cur);
                cur=cur.getLeftNode();
                if (cur!=null) {
                    currentLevel=currentLevel+1;
                }
            }
        }
        //после последнего элемента запятую ставить не нужно - вырезается
        if (str.substring(str.length()-1,str.length()).equals(",")) {
            str=str.substring(0,str.length()-1);
        }
        return str=str+"}";
    }

    //Вертикальный обратный обход https://habr.com/ru/post/144850/
    public boolean isBalance() {
        int currentLevel=0;
        int maxLevel=0;
        int minLevel=0;
        boolean findFirstTerminate = false;
        boolean offCheckFirstTerminate = false;
        Stack<MyNode> stack = new Stack<>();
        MyNode cur = Head;

        if ((Head.getLeftNode()==null)||(Head.getRightNode()==null)) {
            minLevel=0;
        }

        while ((cur!=null)||(!stack.empty())) {
            if (!stack.empty()) {
                //Обрабатываем верхний узел из стека.
                cur=stack.pop();
                currentLevel=cur.getLevel();
                //Если в текущем узле имеется правое поддерево, начинаем следующую итерацию с правого узла.
                //Если правого узла нет, пропускаем шаг со спуском и переходим к обработке следующего узла из стека.
                if (cur.getRightNode()!=null) {
                    cur=cur.getRightNode();
                    currentLevel=currentLevel+1;
                } else {
                    cur=null;
                }
            }
            //из текущего узла «спускаемся» до самого нижнего левого узла, добавляя в стек все посещенные узлы
            while (cur!=null) {
                //вычисляется терминальный узел
                boolean terminate;
                if ((cur.getLeftNode()==null)&&(cur.getRightNode()==null)) {
                    terminate=true;
                    findFirstTerminate=true;
                }
                else {
                    terminate=false;
                }

                if (terminate&&findFirstTerminate&&!offCheckFirstTerminate) {
                    offCheckFirstTerminate=true;
                    maxLevel=currentLevel;
                    if (!((Head.getLeftNode()==null)||(Head.getRightNode()==null))) {
                        minLevel=currentLevel;
                    }
                }

                //при проходе уровень вычисляется и сохраняется у узла для того,
                //чтобы рассчитать относительно него уровень последующих узлов
                cur.setLevel(currentLevel);
                stack.push(cur);

                //обновляется min и max Level
                if (terminate) {
                    if (currentLevel>maxLevel) maxLevel=currentLevel;
                    if (currentLevel<minLevel) minLevel=minLevel;
                }

                cur=cur.getLeftNode();
                if (cur!=null) {
                    currentLevel=currentLevel+1;
                }
            }
        }
        if (Math.abs(maxLevel-minLevel)<=1) {
            return true;
        } else {
            return false;
        }
    }
}

public class DemoMyCollection {
    public static void main(String[] args) {
        try {
            System.out.println("MyList:");
            MyList ml = new MyList(30);
            System.out.println("Создание списка: "+ml.toString());
            ml.add(1,10);
            System.out.println("add в начало: "+ml.toString());
            ml.add(3,40);
            System.out.println("add в конец: "+ml.toString());
            ml.add(2,20);
            System.out.println("add в середину: "+ml.toString());
            System.out.println("getCount: "+ml.getCount());
            System.out.println("get("+"3): "+ml.get(3));
            System.out.println();

            System.out.println("MyTree:");
            MyTree mt = new MyTree(50, 50);
            System.out.println("add 50: "+mt.toString());
            System.out.println("isBalance: "+mt.isBalance());
            mt.add(40,40);
            System.out.println("add 40: "+mt.toString());
            System.out.println("isBalance: "+mt.isBalance());
            mt.add(35,35);
            System.out.println("add 35: "+mt.toString());
            System.out.println("isBalance: "+mt.isBalance());
            mt.add(45,45);
            System.out.println("add 45: "+mt.toString());
            System.out.println("isBalance: "+mt.isBalance());
            mt.add(43,43);
            System.out.println("add 43: "+mt.toString());
            System.out.println("isBalance: "+mt.isBalance());
            mt.add(47,47);
            System.out.println("add 47: "+mt.toString());
            System.out.println("isBalance: "+mt.isBalance());
            mt.add(60,60);
            System.out.println("add 60: "+mt.toString());
            System.out.println("isBalance: "+mt.isBalance());
            mt.add(70,70);
            System.out.println("add 70: "+mt.toString());
            System.out.println("isBalance: "+mt.isBalance());
            mt.add(55,55);
            System.out.println("add 55: "+mt.toString());
            System.out.println("isBalance: "+mt.isBalance());
            mt.add(53,53);
            System.out.println("add 53: "+mt.toString());
            System.out.println("isBalance: "+mt.isBalance());
            mt.add(57,57);
            System.out.println("add 57: "+mt.toString());
            System.out.println("isBalance: "+mt.isBalance());
            System.out.println("getCount: "+mt.getCount());
        }
        catch (Exception e) {
            System.out.println("Ошибка: Обратитесь к администратору:");
            e.printStackTrace();
        }
    }
}