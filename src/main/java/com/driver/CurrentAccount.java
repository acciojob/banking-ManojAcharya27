package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception

        super(name,balance,5000);
        this.tradeLicenseId=tradeLicenseId;
        if(balance<5000) throw new Exception("Insufficient Balance");
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        int n=tradeLicenseId.length();
        boolean flag=false;
        if(n==0) throw new Exception("Valid License can not be generated");
        String res="";
        for(int i=0;i<tradeLicenseId.length()-1;i++){
            if(tradeLicenseId.charAt(i)==tradeLicenseId.charAt(i+1)){
                flag=true;

                int[] count=new int[26];

                for(int j=0;j<tradeLicenseId.length();j++){
                    count[tradeLicenseId.charAt(j)-'a']++;
                }
                char max=getCharMax(count);
                int maxCount=count[(int)max-(int)'a'];
                if(maxCount>(n+1)/2) throw new Exception("Valid License can not be generated");
                for(int j=0;j<n;j++){
                    res+=' ';
                }
                int ind=0;
                while (maxCount>0){
                    res=res.substring(0,ind)+max+res.substring(ind+1);
                    ind=ind+2;
                    maxCount--;
                }
                count[(int)max-(int)'a']=0;
                for(int j=0;j<26;j++){
                    while (count[j]>0){
                        ind=(ind>=n)?1:ind;
                        res=res.substring(0,ind)+(char)((int)+'a'+i)+res.substring(ind+1);
                        ind=ind+2;
                        count[j]--;
                    }
                }
            }
        }
        if(flag) tradeLicenseId=res;

    }
    public char getCharMax(int[] count){
        int max=0;
        char c=' ';
        for(int i=0;i<count.length;i++){
            if(count[i]>max){
                max=count[i];
                c=(char)((int)'a'+i);
            }
        }
        return  c;

    }

}
