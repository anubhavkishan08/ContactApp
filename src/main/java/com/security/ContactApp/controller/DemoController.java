package com.security.ContactApp.controller;


import com.security.ContactApp.dao.User_Details;
import com.security.ContactApp.dao.User_Registration;
import com.security.ContactApp.dto.UserDetailDTO;
import com.security.ContactApp.repository.RegistrationRepository;
import com.security.ContactApp.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class DemoController {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;



//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
//        // Perform authentication logic here
//
//
//         // System.out.println(user.toString());
//         // System.out.println(user.getUserName());
//        if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
//            return ResponseEntity.ok("Login successful");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//        }
//    }
    @PostMapping("/login")
    public RedirectView login(@RequestParam String username, @RequestParam String password) {

            User_Registration user=registrationRepository.findByuserName(username);

        if (user ==null){
            return new RedirectView("/html/error.html"); // Redirect back to login with error
        }

        if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
            return new RedirectView("/html/viewcontacts.html"); // Redirect to dashboard page
        } else {
            return new RedirectView("/html/error.html"); // Redirect back to login with error
        }
    }

//    @GetMapping("/login_fail")
//    public RedirectView loginFail(){
//        return new RedirectView("html/error.html");
//    }

    @GetMapping("/home")
    public RedirectView home(){
        return new RedirectView("/html/home.html");
    }

    @PostMapping("/register")
    public RedirectView register(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password) {

        User_Registration user_registration = new User_Registration();
        user_registration.setUserName(username);
        user_registration.setEmail(email);
        user_registration.setPassword(password);

        registrationRepository.save(user_registration);


        return new RedirectView("html/addcontact.html");
    }

    @PostMapping("/add")
    public RedirectView addContact(@RequestParam String name, @RequestParam String email,
                                   @RequestParam String facebook, @RequestParam String linkedin, @RequestParam String mobno,
                                    Principal principal) {


        try {
            // Find the logged-in user's registration details using Principal
            User_Registration userRegistration = registrationRepository.findByuserName(principal.getName());

           // byte[] imageBytes = image.getBytes();
            User_Details userDetails = new User_Details();
            userDetails.setName(name);
            userDetails.setEmailId(email);
            userDetails.setFbId(facebook);
            userDetails.setLinkedinId(linkedin);
            userDetails.setMobNo(mobno);
          //  userDetails.setImage(imageBytes);
            userDetails.setUserRegistration(userRegistration);

            //System.out.println(userRegistration.toString());
            userDetailsRepository.save(userDetails);
            System.out.println("Details saved......");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new RedirectView("html/addcontact.html");
    }

//    @GetMapping("/user-details")
//    public  List<User_Details> getUserDetails(Model model, Principal principal) {
//            if (principal == null) {
//                return Collections.emptyList();
//           }
//
//        String username = principal.getName(); // Get logged-in user's username
//       // System.out.println(username);
//        User_Registration user = registrationRepository.findByuserName(username);
//        List<User_Details> userDetailsList = user.getUserDetailsList();
//
//        System.out.println(userDetailsList);
//
//        return userDetailsList;
//    }

    @GetMapping("/user-details")
    public List<UserDetailDTO> getUserDetails(Principal principal) {
        if (principal == null) {
            return Collections.emptyList();
        }

        String username = principal.getName(); // Get logged-in user's username
        User_Registration user = registrationRepository.findByuserName(username);
        List<User_Details> userDetailsList = user.getUserDetailsList();

        // Convert to DTOs
        List<UserDetailDTO> dtoList = new ArrayList<>();
        for (User_Details detail : userDetailsList) {
            UserDetailDTO userDetailDTO = new UserDetailDTO(
                    detail.getId(),
                    detail.getName(),
                    detail.getEmailId(),
                    detail.getFbId(),
                    detail.getLinkedinId(),
                    detail.getMobNo());
            dtoList.add(userDetailDTO);
        }

        return dtoList;
    }
    @DeleteMapping("/delete-contact/{contactId}")
    public ResponseEntity<Void> deleteContact(@PathVariable int contactId, Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403 Forbidden if the user is not authenticated
        }

        String username = principal.getName(); // Get the logged-in user's username
        User_Registration user = registrationRepository.findByuserName(username);

        // Find the contact by ID
        User_Details userDetails = userDetailsRepository.findById(contactId).orElse(null);

        if (userDetails != null && userDetails.getUserRegistration().getId() == user.getId()) {
            // Delete the contact if it belongs to the logged-in user
            userDetailsRepository.deleteById(contactId);

            return ResponseEntity.noContent().build(); // 204 No Content if successful
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found if the contact does not exist or does not belong to the user
        }
    }

    @PutMapping("/update-contact/{contactId}")
    public ResponseEntity<User_Details> updateContact(
            @PathVariable int contactId,
            @RequestBody User_Details updatedContact,
            Principal principal) {

        if (principal == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403 Forbidden if the user is not authenticated
        }

        String username = principal.getName(); // Get the logged-in user's username
        User_Registration user = registrationRepository.findByuserName(username);

        // Find the contact by ID
        User_Details existingContact = userDetailsRepository.findById(contactId).orElse(null);

        if (existingContact != null && existingContact.getUserRegistration().getId() == user.getId()) {
            // Update the existing contact's details
            existingContact.setName(updatedContact.getName());
            existingContact.setEmailId(updatedContact.getEmailId());
            existingContact.setFbId(updatedContact.getFbId());
            existingContact.setLinkedinId(updatedContact.getLinkedinId());
            existingContact.setMobNo(updatedContact.getMobNo());
            // Save the updated contact
            userDetailsRepository.save(existingContact);
            System.out.println("Details updated");

            return ResponseEntity.ok(existingContact); // 200 OK with the updated contact
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found if the contact does not exist or does not belong to the user
        }
    }

    @GetMapping("/current-username")
    public ResponseEntity<String> getCurrentUsername(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not authenticated");
        }
        return ResponseEntity.ok(principal.getName());
    }
}
