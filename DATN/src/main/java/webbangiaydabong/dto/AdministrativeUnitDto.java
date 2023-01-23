package webbangiaydabong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import webbangiaydabong.entity.AdministrativeUnit;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AdministrativeUnitDto {
    private Long id;
    private String name;
    private String code;
    private Integer level;
    private AdministrativeUnitDto parent;
    private List<AdministrativeUnitDto> children;
    private Long parentId;
    private String parentName;
    private String parentCode;


    public AdministrativeUnitDto(AdministrativeUnit entity) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.name = entity.getName();
        this.level = entity.getLevel();
    }

    public AdministrativeUnitDto(AdministrativeUnit entity, Boolean includeChild) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.name = entity.getName();
        this.level = entity.getLevel();
        if(entity.getParent() != null) {
            this.parent = new AdministrativeUnitDto(entity.getParent(), false);
            this.parentId = entity.getParent().getId();
        }

        if(includeChild) {
            if(entity.getSubAdministrativeUnits() != null && entity.getSubAdministrativeUnits().size() > 0) {
                List<AdministrativeUnitDto> subs = new ArrayList<>();
                for(AdministrativeUnit unit : entity.getSubAdministrativeUnits()) {
                    subs.add(new AdministrativeUnitDto(unit, false));
                }
                this.children = subs;
            }
        }

    }
    public AdministrativeUnitDto(AdministrativeUnit entity, Boolean includeParent, Boolean includeChild) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.name = entity.getName();
        this.level = entity.getLevel();

        if (includeParent && entity.getParent() != null) {
            this.parent = new AdministrativeUnitDto(entity.getParent());
            this.parentId = entity.getParent().getId();
        }

        if (includeChild && entity.getSubAdministrativeUnits() != null
                && entity.getSubAdministrativeUnits().size() > 0) {
            List<AdministrativeUnitDto> subs = new ArrayList<>();
            for (AdministrativeUnit unit : entity.getSubAdministrativeUnits()) {
                subs.add(new AdministrativeUnitDto(unit, true, false));
            }
            this.children = subs;
        }

    }
}
