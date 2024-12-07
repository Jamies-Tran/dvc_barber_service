package com.project.dvc_barber_service.service.branch;

import com.project.dvc_barber_service.config.context.RequestContext;
import com.project.dvc_barber_service.config.handler.exception.ResourceConflictException;
import com.project.dvc_barber_service.dto.account.AccountLogin;
import com.project.dvc_barber_service.dto.branch.Branch;
import com.project.dvc_barber_service.dto.branch.IBranchMapper;
import com.project.dvc_barber_service.dto.branch.action.BranchCreateAction;
import com.project.dvc_barber_service.repository.database.branch.BranchEntity;
import com.project.dvc_barber_service.repository.database.branch.IBranchRepository;
import com.project.dvc_barber_service.repository.feign.place.geo.models.Address;
import com.project.dvc_barber_service.repository.feign.place.geo.models.AddressComponent;
import com.project.dvc_barber_service.repository.feign.place.geo.models.AddressResult;
import com.project.dvc_barber_service.repository.feign.place.geo.models.LocationGeometry;
import com.project.dvc_barber_service.service.geocoding.ForwardGeocodingService;
import com.project.dvc_barber_service.util.PrepareSaveOrUpdate;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BranchCommandService {
    @NonNull IBranchRepository repository;

    @NonNull IBranchMapper mapper;

    @NonNull ForwardGeocodingService geocodingService;

    @NonNull RequestContext requestContext;

    /*
    * Lưu chi nhánh
    * */
    public Branch save(BranchCreateAction action) {
        try {
            Branch branch = action.branch();
            AccountLogin accountLogin = requestContext.getAccount();
            if(repository.existsByAddress(branch.address())) {
                throw new ResourceConflictException("Đã có chi nhánh hoạt động trên địa chỉ này");
            }
            AddressResult addressResult = getAddressResult(branch.address());
            LocationGeometry geometry = addressResult.geometry().locationGeometry();
            BranchEntity newBranch = mapper.toEntity(branch);
            newBranch.setAddress(addressResult.addressDetail());
            newBranch.setLatitude(geometry.latitude());
            newBranch.setLongitude(geometry.longitude());
            PrepareSaveOrUpdate.prepareSave(newBranch, accountLogin);
            BranchEntity savedBranch = repository.save(newBranch);

            return mapper.toDto(savedBranch);
        } catch (ResourceConflictException e) {
            throw e;
        } catch (Exception e) {
            log.error("[{}-save] Có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
            throw e;
        }
    }

    private AddressResult getAddressResult(String address) {
        Address goongAddress = geocodingService.forwardGeocoding(address);
        return goongAddress.results().getFirst();
    }
    /*
    * Use case
    * end
    * */
}
