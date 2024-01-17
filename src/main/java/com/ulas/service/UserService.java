package com.ulas.service;

import com.ulas.dto.request.CreateUserRequestDto;
import com.ulas.dto.request.LoginUserRequestDto;
import com.ulas.dto.request.UpdateFlightsRequestDto;
import com.ulas.dto.request.UpdateUserRequestDto;
import com.ulas.exception.EErrorType;
import com.ulas.exception.UserManagerException;
import com.ulas.mapper.IAirportMapper;
import com.ulas.mapper.IFlightsMapper;
import com.ulas.mapper.IUserMapper;
import com.ulas.repository.IFlightsRepository;
import com.ulas.repository.IUserRepository;
import com.ulas.repository.entity.Airport;
import com.ulas.repository.entity.Flights;
import com.ulas.repository.entity.User;
import com.ulas.repository.enums.EStatus;
import com.ulas.utilty.JwtTokenManager;
import com.ulas.utilty.ServiceManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService extends ServiceManager<User,Long> {
    private  final IUserRepository iUserRepository;
    private final JwtTokenManager jwtTokenManager;

    public UserService(IUserRepository iUserRepository, JwtTokenManager jwtTokenManager) {
        super(iUserRepository);
        this.iUserRepository = iUserRepository;
        this.jwtTokenManager = jwtTokenManager;
    }

    public User createUser(CreateUserRequestDto dto) {
        Optional<User> existUserName = iUserRepository.findByUserName(dto.getUserName());
        if (existUserName.isPresent())
            throw new IllegalStateException(dto.getUserName()+" is used.");
        if(!dto.getPassword().equals(dto.getRePassword()))
            throw new IllegalStateException("Password is unmatch");
        User user = IUserMapper.INSTANCE.toUser(dto);
        save(user);
        return user;
    }
    public User updateUser(UpdateUserRequestDto dto) {
        Optional<User> existUser = iUserRepository.findById(dto.getId());

        if (existUser.isEmpty() || existUser.get().getStatus().equals(EStatus.DELETED))
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        if(!dto.getPassword().equals(dto.getRePassword()))
            throw new UserManagerException(EErrorType.USER_PASSWORD_UNMATCHED);
        User updatedUser = IUserMapper.INSTANCE.toUpdateUser(dto);
        updatedUser.setUserName(existUser.get().getUserName());
        updatedUser.setId(existUser.get().getId());
        updatedUser.setCreateDate((updatedUser.getCreateDate()));
        updatedUser.setUpdateDate(System.currentTimeMillis());
        update(updatedUser);
        return updatedUser;
    }

    public User deleteUser(Long id) {
        Optional<User> existUser = iUserRepository.findById(id);
        if (existUser.isEmpty() || existUser.get().getStatus().equals(EStatus.DELETED))
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        existUser.get().setStatus(EStatus.DELETED);
        update(existUser.get());
        return existUser.get();
    }
    public String login(LoginUserRequestDto dto) {
        Optional<User> userAuth = iUserRepository.findByUserNameAndPassword(dto.getUserName(),dto.getPassword());
        if (userAuth.isEmpty())
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        if(userAuth.get().getStatus()==EStatus.DELETED)
            throw new UserManagerException(EErrorType.LOGIN_ERROR_USERNAME_PASSWORD);
        String authStatus = userAuth.get().getStatus().toString();
        String token = jwtTokenManager.createToken(userAuth.get().getId(),userAuth.get().getRole(), userAuth.get().getStatus(),userAuth.get().getUserName())
                .orElseThrow(()->{
                    throw new UserManagerException(EErrorType.TOKEN_NOT_CREATED);
                });
        System.out.println("token==>"+token);
        return token;
    }

    public ResponseEntity<?> validateToken(String token) {
        boolean isValid = jwtTokenManager.verifyToken(token);
        if (isValid){
            return ResponseEntity.ok().body("Token is valid.");
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token.");
        }
    }
}
