package uz.pdp.appcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcompany.entity.Address;
import uz.pdp.appcompany.entity.Department;
import uz.pdp.appcompany.entity.Worker;
import uz.pdp.appcompany.payload.Result;
import uz.pdp.appcompany.payload.WorkerDto;
import uz.pdp.appcompany.repository.AddressRepository;
import uz.pdp.appcompany.repository.DepartmentRepository;
import uz.pdp.appcompany.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    AddressRepository addressRepository;

    public List<Worker> getWorker(){
        return workerRepository.findAll();
    }

    public Worker getWorkerById(Integer id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        return optionalWorker.orElseGet(Worker::new);
    }

    public Result addWorker(WorkerDto workerDto){
        Worker addWorker=new Worker();
        addWorker.setName(workerDto.getName());

        boolean existsByPhoneNumber = workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber());
        if (existsByPhoneNumber)
            return new Result("Phone number already exist, Enter another ni=umber", false);
        addWorker.setPhoneNumber(workerDto.getPhoneNumber());

        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent())
            return new Result("Department not found", false);
        addWorker.setDepartment(optionalDepartment.get());

        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        if (!optionalAddress.isPresent())
            return new Result("Address not found", false);
        addWorker.setAddress(optionalAddress.get());

        workerRepository.save(addWorker);
        return new Result("Address saved", true);
    }

    public Result deleteWorker(Integer id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent())
            return new Result("Worker not found", false);
        workerRepository.deleteById(id);
        return new Result("Worker deleted", true);
    }

    public Result editWorker(Integer id, WorkerDto workerDto){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent())
            return new Result("Worker not found", false);
        Worker worker = optionalWorker.get();
        worker.setName(workerDto.getName());

        boolean existsByPhoneNumberAndIdNot = workerRepository.existsByPhoneNumberAndIdNot(worker.getPhoneNumber(), id);
        if (existsByPhoneNumberAndIdNot)
            return new Result("Phone number already exist, Enter another number", false);
        worker.setPhoneNumber(workerDto.getPhoneNumber());

        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent())
            return new Result("Department not found", false);
        worker.setDepartment(optionalDepartment.get());

        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        if (!optionalAddress.isPresent())
            return new Result("Address not found", false);
        worker.setAddress(optionalAddress.get());

        workerRepository.save(worker);
        return new Result("Worker edited", true);
    }
}
