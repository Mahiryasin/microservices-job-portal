public class coffe {

    private String name;
    private String surname;
    private Long age;

    public coffe(Builder builder){
        this.name=builder.name;
        this.surname=builder.surname;
        this.age=builder.age;

    }

    public static class Builder{
        private String name;
        private String surname;
        private Long age;

        public Builder(String name){
            this.name=name;
        }
        public Builder GetSurname(String surname){
            this.surname=surname;
            return this;
        }
        public Builder GetAge(Long age){
            this.age=age;
            return this;
        }
        public coffe Build(){
            return new coffe(this);
        }
        
    }
}
