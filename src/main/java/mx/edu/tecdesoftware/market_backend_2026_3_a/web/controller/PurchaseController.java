package mx.edu.tecdesoftware.market_backend_2026_3_a.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.Purchase;
import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    // GET /purchases -> listado completo de compras
    @GetMapping("")
    @Operation(summary = "Get all purchases",description = "Return a list of all purchases")

    @ApiResponse(responseCode = "200", description = "Purchases successfully retrieved")
    @ApiResponse(responseCode = "404", description = "Purchases not found")
    @ApiResponse(responseCode = "500", description = "Internal Server error")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<List<Purchase>> getAll(){
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    // GET /purchases/client/{clientId} -> compras filtradas por cliente
    @GetMapping("/client/{clientId}")
    @Operation(summary = "Get a purchase by its client",description = "Return a list of purchases by its client ID if it exists")

    @ApiResponse(responseCode = "200", description = "Purchase/s found")
    @ApiResponse(responseCode = "404", description = "Purchase/s not found")
    @ApiResponse(responseCode = "500", description = "Internal Server error")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<List<Purchase>> getByClient(@PathVariable("clientId") String clientId){
        return purchaseService.getByClient(clientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //POST /purchases/save -> registrar una nueva compra (con sus productos en cascada)
    @PostMapping("/save")
    @Operation(
            summary = "Save a new purchases",
            description = "Register a new purchase and return the created purchase",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json", // <-- Agrega el mediaType
                            examples = @ExampleObject(
                                    name = "Example purchases",
                                    value = """
                                {
                                  "clientId": "4546221",
                                  "date": "2026-07-16T14:33:37.335Z",
                                  "paymentMethod": "E",
                                  "comment": "Compra",
                                  "status": "T",
                                  "items": [
                                    {
                                      "productId": 4,
                                      "quantity": 1,
                                      "price": 0.1,
                                      "status": true
                                    }
                                  ]
                                }
                                """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Purchase created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid purchase data")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "409", description = "Purchase conflict (duplicate)")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }
}
