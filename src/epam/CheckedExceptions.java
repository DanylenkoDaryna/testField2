package epam;

import java.io.IOException;

public class CheckedExceptions {

    public static void main(String[] args) throws Exception {
        System.out.println("Hi adsjfsfh");
        Throwable tr = new Exception("fghfkj");
        Exception tr2 = new Exception("fgsdfshfkj");

        //example 1
        example1();

        //example 2
        System.out.println(example2());

        //example3
        example3();

        //example4
        //Если в блоке catch происходит выброс исключения, то его обработкой не будут
        // заниматься следующие за данным блоки catch.
        example4();
        //Для корректной обработки подобной ситуации следует весь код try/catch
        // поместить во внешний блок try/catch.
        example41();

        //example5
        //Если за блоком try/catch следует блок finally, то его содержимое выполнится
        // всегда в независимости от того, произойдут или нет выбросы исключений в
        // блоках try и catch.
        example5();

        //example6
        //Если в catch блоке происходит передача управления с помощью оператора return, то
        // значение его операнда запоминается, выполняется содержимое блока finally
        // (если он есть), после чего происходит выполнение оператора return, которому в
        // качестве операнда будет передано сохраненное значение.
        //Таким образом, выполнение содержимого блока finally не влияет на
        // возвращаемое оператором return значение.
        System.out.println(example6());

        //Замечание. Если finally блок содержит оператор return, то выход из
        // try/catch/finally блока будет происходить по оператору return,
        // который содержится в finally блоке.
        System.out.println(example61());

        //example8
        /*4) вызов метода поместить в блок try/catch, в котором извлечь исходное
         исключение при помощи метода getCause: */
        B1 b = new B1();
        try {
            b.m();
        } catch (Throwable t) { // перехват исключения
            Throwable cause = t.getCause();
            System.out.println(cause);
        }

    }

    private static void example1() throws NoSuchMethodException {
        try {
            System.out.println("111111");
            throw new NoSuchMethodException();
            //System.out.println("222222222"); - not reachable
        } catch (Exception ex) {
            System.out.println("33333333333");
            throw ex;
            //System.out.println("4444444444"); - unreachable
        }
        // System.out.println("55555555");- unreachable
    }

    private static int example2() {
        try {
            System.out.println("111111");
            throw new SecurityException();
            //System.out.println("222222222"); - not reachable
        } catch (Exception ex) {
            System.out.println("33333333333");
            return 2;
            //System.out.println("4444444444"); - unreachable
        }
        // System.out.println("55555555");- unreachable
    }

    private static void example3() {
        Boolean a, b, c;
        a = b = c = false;
        String s = null;
        try {
            // выброс исключения Ex которое имеет тип NullPointerException:
            int y = s.length();
            a = true; // не будет выполнено
        } catch (NullPointerException e) {
            b = true; // будет выполнено
        }
        c = true; // будет выполнено
    }


    private static void example4() throws Exception {
        try {
            // выброс исключения Ex1:
            throw new NullPointerException();
        } catch (NullPointerException e) {
            // выброс исключения Ex2,
            // который приведет к выходу из блока try/catch:
            throw new MyOwnException("haha");
        } catch (Exception e) {
            // данный блок catch не будет обрабатывать Ex2
        }
    }

    private static void example41()  {
        try {
            try {
                // выброс исключения Ex1 :
                throw new NullPointerException();
                // обработка исключения Ex 1:
            } catch(NullPointerException e) {
                // выброс исключения Ex2, который приведет
                // к выходу из блока try/catch:
                throw new MyOwnException("MyOwnException");
            }
        } catch(Exception e) {
            System.out.println("обработка исключения Ex2");
        }
    }

    private static void example5() throws MyOwnException {
        try {
            throw new Exception(); // выброс исключения Ex1
        } catch (Exception e) { // обработка Ex1
            // выброс исключения Ex2, выход из блока catch:
            throw new MyOwnException("example5");
        } finally {
            // перед выходом выполнится тело блока finally
            System.out.println("finally block is always executed");
        }

    }

    private static int example6() throws MyOwnException {
        int x = 1;
        try {
            throw new Exception(); // выброс исключения
        } catch (Exception E) { // перехват исключения
            return x = 2; // значение 2 запоминается
        } finally {
            // выполняется перед выходом
            // по оператору return:
            x = 3;
        }

    }

    private static int example61() throws MyOwnException {
        int x = 1;
        try {
            throw new Exception(); // выброс исключения
        } catch (Exception E) { // перехват исключения
            return x = 2; // значение 2 запоминается
        } finally {
            // выполняется перед выходом
            // по оператору return:
            return x = 3333;
        }

    }
}

///////////////////////////////////////////////////
/*example7
При перекрытии метода не может быть расширено множество тех исключений, которые задекларированы
в секции throws.
 */
class A {
    void m() throws IOException {}
}

class B extends A {
    // ошибка компиляции, расширено множество
    // выбрасываемых исключений:
    //void m() throws Exception {}
}
/*Замечание. При проверке расширено или нет множество выбрасываемых методом исключений не
 учитываются исключения, которые относятся к иерархиям java.lang.Error и
 java.lang.RuntimeException.
  */
///////////////////////////////////////////////////


///////////////////////////////////////////////////
/*example8
Если перекрываемый в классе потомке метод потенциально может выбросить исключения,
которые не задекларированы в секции throws метода класса предка, то следует: */
class A1 {
    // выбрасывает только Error или RuntimeException:
    void m() {}
}
class B1 extends A1 {
    void m() {
       // 1) перехватить такие исключения;
        try {
            throw new IOException();
        } catch (Exception e) {
           //2) поместить их вовнутрь исключений из иерархии RuntimeException или Error;
           // 3) принудительно выбросить созданные исключения;
            throw new Error(e);
        }
    }
}

///////////////////////////////////////////////////

class MyOwnException extends Exception{

    private String msg;

    MyOwnException(String str){
        msg=str;
    }

    /*Метод toString возвращает строку, составленную из имени класса
     исключения, двоеточия с пробелом и строки, которую возвращает метод getMessage. */

    @Override
    public String toString() {
        return "MyOwnException: " + getMessage();
    }

    /*Метод getMessage возвращает описание исключения.
     При определении пользовательского класса исключений можно перекрыть оба этих метода.
     Однако, как правило, перекрывают только метод getMessage.     */
    @Override
    public String getMessage() {
        return msg;
    }
}

