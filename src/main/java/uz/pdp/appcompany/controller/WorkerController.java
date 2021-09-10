package uz.pdp.appcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcompany.entity.Worker;
import uz.pdp.appcompany.payload.Result;
import uz.pdp.appcompany.payload.WorkerDto;
import uz.pdp.appcompany.service.WorkerService;

import java.util.List;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;

    @GetMapping
    public ResponseEntity<List<Worker>> getWorker() {
        List<Worker> workerList = workerService.getWorker();
        return ResponseEntity.ok(workerList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> getWorker(@PathVariable Integer id) {
        Worker workerById = workerService.getWorkerById(id);
        return ResponseEntity.ok(workerById);
    }

    @PostMapping
    public ResponseEntity<Result> addWorker(@RequestBody WorkerDto workerDto) {
        Result addWorker = workerService.addWorker(workerDto);
        return ResponseEntity.status(addWorker.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(addWorker);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> editWorker(@PathVariable Integer id, @RequestBody WorkerDto workerDto) {
        Result editWorker = workerService.editWorker(id, workerDto);
        return ResponseEntity.status(editWorker.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(editWorker);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteWorker(@PathVariable Integer id) {
        Result deleteWorker = workerService.deleteWorker(id);
        return ResponseEntity.status(deleteWorker.isSuccess() ? 202 : 409).body(deleteWorker);
    }
}
