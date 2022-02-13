public class ClassForTesting {
    private Integer a;
    private Integer b;
    public Integer c;
    public Integer sum;
    public Integer result;
    private String str = "Программа вычисления суммы";


    @BeforeSuite
    private String getStr (){
        return str;
   }


    @Test
    public boolean setIntegerA (Integer a){
        this.a = a;
        return true;
    }

    @Test
    private boolean setIntegerB (Integer b){
        this.b = b;
        return true;
    }

    @Test
    public boolean setIntegerC (Integer c){
        this.c = c;
        return true;
    }

    @Test(priority = 2)
    public Integer getSum (){
        sum = a+b+c;
        return sum;
    }

    @Test(priority = 5)
    public Integer getResult(){
        result = sum/a;
        return result;
    }

    @AfterSuite
    public String outPut (){
        return "Результат вычисления: " + result;
    }


}
