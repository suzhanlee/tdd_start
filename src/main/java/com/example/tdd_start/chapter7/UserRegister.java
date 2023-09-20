package com.example.tdd_start.chapter7;

public class UserRegister {

    private WeakPasswordChecker passwordChecker;
    private UserRepository userRepository;
    private EmailNotifier emailNotifier;

    public UserRegister(WeakPasswordChecker passwordChecker, UserRepository userRepository,
        EmailNotifier emailNotifier) {
        this.passwordChecker = passwordChecker;
        this.userRepository = userRepository;
        this.emailNotifier = emailNotifier;
    }

    public void register(String id, String pw, String email) {
        if (passwordChecker.checkPasswordWeak(pw)) {
            throw new WeakPasswordException();
        }
        User user = userRepository.findById(id);
        if (user != null) {
            throw new DupIdException();
        }
        userRepository.save(new User(id, pw, email));

        // spy의 이메일 발송 기능 구현에서 호출 여부 기록
        emailNotifier.sendRegisterEmail(email);
    }
}
