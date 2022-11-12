package webbangiaydabong.service.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import webbangiaydabong.dto.AccountDTO;
import webbangiaydabong.entity.Account;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountMapper implements EntityMapper<AccountDTO, Account> {
    @Override
    public Account toEntity(AccountDTO dto) {
        if (dto == null) {
            return null;
        }

        Account entity = new Account();
        BeanUtils.copyProperties(dto, entity);

        return entity;
    }

    @Override
    public AccountDTO toDto(Account entity) {
        if (entity == null) {
            return null;
        }

        AccountDTO dto = new AccountDTO();
        BeanUtils.copyProperties(entity, dto);

        return dto;
    }

    @Override
    public List<Account> toEntity(List<AccountDTO> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<AccountDTO> toDto(List<Account> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
