/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;

/**
 *
 * @author megbl
 

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aladi
 */
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;;


public class smsSender2 {
    public static final String ACCOUNT_SID = "AC3106843fa0376259f9f90aedd26ae2e2";
    public static final String AUTH_TOKEN = "40f6829003da778b432ae8337f8cb27e";

    public static void sendSms(String toPhoneNumber, String message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message sms = Message.creator(
                new PhoneNumber(toPhoneNumber),
                new PhoneNumber("+12256358461"),
                "votre reclamation a ete recu"
          
        ).create();

        System.out.println("Message SID: " + sms.getSid());
    }
}
