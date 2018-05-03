package com.example.eodhuno.ebellesalon;

public  class NewCustomer {

        private String Fname, LName, PhoneNo, Email,Password;


        public NewCustomer(){}

        public NewCustomer(String Fname, String LName,String PhoneNo, String Email, String Password){
            this.Fname = Fname;
            this.LName = LName;
            this.PhoneNo = PhoneNo;
            this.Email = Email;
            this.Password = Password;
        }

        public String getFname() {
            return Fname;
        }

        public void setFname(String Fname) {
            this.Fname = Fname;
        }

        public String getLName() { return LName; }

        public void setLName(String LName) {this.LName = LName; }

        public String getPhoneNo () { return PhoneNo; }

        public void setPhoneNo (String PhoneNo) {this.PhoneNo = PhoneNo;}

        public String getEmail(){return Email;}

        public void setEmail(String Email) {this.Email = Email;}

        public String getPassword() {return Password;}

        public void setPassword (String Password) {this.Password = Password;}

}


