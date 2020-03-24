package main.java;

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
        this.count = 1;
        this.firstElm = new MyElmList(data,null,null);
        this.endElm = this.firstElm;
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
            if (i<count) {
                str=str+"{"+cur.getData().toString()+"},";
            }
            if (i==count) {
                str=str+"{"+cur.getData().toString()+"}";
            }
            cur=cur.getNextElmList();
        }
        str=str+"}";
        return str;
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
        }
        catch (Exception e) {
            System.out.println("Ошибка: Обратитесь к администратору:");
            e.printStackTrace();
        }
    }
}