package com.oopsfeedmecode.userservice;

import com.oopsfeedmecode.userservice.service.ValidationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    public User createUser(UserRequest request) {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        return userRepository.save(mapRequestToUser(request));
    }

    private static User mapRequestToUser(UserRequest request) {
        return Optional.ofNullable(request)
                .map(req -> {
                    User user = new User();
                    user.setEmail(req.getEmail());
                    user.setFirstName(req.getFirstName());
                    user.setLastName(req.getLastName());
                    user.setPhone(req.getPhone());
                    user.setId(req.getId());
                    user.setPassword(req.getPassword());
                    return user;
                })
                .orElseThrow(() -> new IllegalArgumentException("Request cannot be null"));
    }

    public User updateUser(UserRequest request) {
        User fromDb = userRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        BeanUtils.copyProperties(request, fromDb, getNullPropertyNames(request));

        return userRepository.save(fromDb);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

    // Additional complex function with business validation
    public void complexBusinessFunction(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        validationService.validate(user);

        // Further complex operations can be added here
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}