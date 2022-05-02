package capstone.controllers;

import capstone.domain.MessageService;
import capstone.domain.Result;
import capstone.models.Message;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<Message> findAll() {
        return messageService.findAll();
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<Message> findById(@PathVariable int messageId) {
        Message message = messageService.findById(messageId);
        if(message == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Message> add(@RequestBody Message message) {
        Result<Message> messageResult = messageService.add(message);
        if(!messageResult.isSuccess()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(messageResult.getPayload(), HttpStatus.CREATED);
    }

    @PutMapping("/{messageId}")
    public ResponseEntity<Message> update(@RequestBody Message message, @PathVariable int messageId) {
        if(message.getMessageId() != messageId) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

        Result<Message> messageResult = messageService.update(message);
        if(!messageResult.isSuccess()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(messageResult.getPayload(), HttpStatus.OK);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> deleteById(@PathVariable int messageId) {
        messageService.deleteById(messageId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
