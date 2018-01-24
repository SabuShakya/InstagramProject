package com.f1soft.admin.repository;

import com.f1soft.admin.dto.TokenAuthDto;
import com.f1soft.admin.model.TokenAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenAuth,Integer>{
    public TokenAuth getByAdmin_Id(int id);
    public TokenAuth getByAdmin_IdAndTokenNo(int id ,String tokenNo);
}
