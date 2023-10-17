package com.example.shipnhanh.exception;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class Validate {

    public Double convertLatitude( double latiTude){
        double rounded = Math.round(latiTude * 1e6) / 1e6;
        if(latiTude ==0){
            throw new MessLatitudeError("Vĩ độ không được bỏ trống");
        }else if(isValidLatitude(String.valueOf(rounded))==true){
            return rounded;
        }else{
            throw new MessLatitudeError("Vĩ độ được định dạng từ -90 đến 90, bạn hãy thử lại");
        }

    }
    public Double convertLongitude( double longitude){
        double rounded = Math.round(longitude * 1e6) / 1e6;
        if(longitude == 0){
            throw new MessLongitudeError("Kinh độ không được bỏ trống");
        }else if(isValidLongitude(String.valueOf(rounded))==true){
            return rounded;
        }else{
            throw new MessLongitudeError("Kinh độ được định dạng từ -180 đến 180, bạn hãy thử lại");
        }

    }

    public String convertNumberPhone(String numberPhone){
        numberPhone=numberPhone.trim();
        String check="";
        if (numberPhone==null||numberPhone.length()==0){
            throw new MessNumberPhoneError("Số điện thoại không được bỏ trống");
        }
        if(numberPhone.length()==9){
            check="0"+numberPhone;
            if(isValidPhoneNumber(check)==true){
                return check;
            }else{
                throw new MessNumberPhoneError("Số điện thoại sai định dạng, bạn hãy thử lại");
            }
        }
        if(numberPhone.length()==10){
            check=numberPhone;
            if(isValidPhoneNumber(check)==true){
                return check;
            }else{
                throw new MessNumberPhoneError("Số điện thoại sai định dạng, bạn hãy thử lại");
            }
        }
        throw new MessNumberPhoneError("Số điện thoại sai định dạng, bạn hãy thử lại");
    }
    private static boolean isValidLatitude(String latitude) {
        String regex = "^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?))$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(latitude);

        return matcher.matches();
    }

    private static boolean isValidLongitude(String longitude) {
        String regex = "^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?))$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(longitude);

        return matcher.matches();
    }
    private static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "(0[3|5|7|8|9])+([0-9]{8})\\b";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }

    public String isValidateString(String text) {
        if(text ==null ||text.length()==0){
            throw new MessStringError("Bạn nhập thiếu 1 trường nào đó hãy kiểm tra và thử lại");
        }
        if(text.length() > 250){
            throw new MessStringError("1 trường nào đó đã quá 250 ký tự bạn hãy kiểm tra và thử lại");
        }

        return convertText(text);
    }

    private static String convertText(String text){
        String check = text.trim();
        boolean regex = false;
        while (!regex){
            if(check.contains("  ")){
                check = check.replace("  "," ");
                regex = false;
            }else regex = true;
        }
        return check;
    }
}
