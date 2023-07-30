package com.jafar.pockesoz.service.impl;

import com.jafar.pockesoz.entity.Pocket;
import com.jafar.pockesoz.entity.User;
import com.jafar.pockesoz.entity.constant.PocketType;
import com.jafar.pockesoz.model.request.PocketRequest;
import com.jafar.pockesoz.model.response.PocketResponse;
import com.jafar.pockesoz.model.response.UserResponse;
import com.jafar.pockesoz.repository.PocketRepository;
import com.jafar.pockesoz.service.PocketService;
import com.jafar.pockesoz.service.UserService;
import com.jafar.pockesoz.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PocketServiceImpl implements PocketService {

    private final PocketRepository pocketRepository;
    private final ValidationUtil validationUtil;
    private final UserService userService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public PocketResponse create(PocketRequest request) {
        validationUtil.validate(request);
        User user = userService.getUserById(request.getUserId());
        UserResponse userResponse = userService.getById(request.getUserId());

        Pocket pocket = Pocket.builder()
                .name(request.getName())
                .user(user)
                .balance(0L)
                .type(PocketType.get(request.getType()))
                .isActive(true)
                .build();
        pocketRepository.saveAndFlush(pocket);

        return PocketResponse.builder()
                .id(pocket.getId())
                .name(request.getName())
                .balance(pocket.getBalance())
                .type(String.valueOf(pocket.getType()))
                .userResponse(userResponse)
                .build();
    }

    @Override
    public PocketResponse getById(String id) {
        Pocket pocket = findByIdOrThrowError(id);

        return getPocketResponse(pocket);
    }

    @Override
    public Pocket getPocketById(String id) {
        return findByIdOrThrowError(id);
    }

    @Override
    public List<Pocket> getAllByUserId(String userId) {
        List<Pocket> pockets = pocketRepository.findAllByUser_Id(userId);
        if (pockets.isEmpty()) throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "pockets not found");

        return pockets.stream()
                .filter(Pocket::getIsActive)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public PocketResponse update(PocketRequest request) {
        Pocket pocket = findByIdOrThrowError(request.getId());
        pocket.setName(request.getName());
        pocket.setType(PocketType.get(request.getType()));

        return getPocketResponse(pocket);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteById(String id) {
        Pocket pocket = findByIdOrThrowError(id);
        pocket.setIsActive(false);
    }

    private Pocket findByIdOrThrowError(String id) {
        return pocketRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "pocket not found"));
    }

    private PocketResponse getPocketResponse(Pocket pocket) {
        User user = pocket.getUser();
        UserResponse userResponse = UserResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();

        return PocketResponse.builder()
                .id(pocket.getId())
                .name(pocket.getName())
                .balance(pocket.getBalance())
                .type(String.valueOf(pocket.getType()))
                .userResponse(userResponse)
                .build();
    }
}
