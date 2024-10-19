package thomson.reuters.catalog.api.web;

import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.GetMapping;

public class InvestmentController {

    private final ThomsonReutersAPIExample apiService;

    public InvestmentController(ThomsonReutersAPIExample apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/investment-data")
    public JsonObject getInvestmentData() {
        return apiService.getInvestmentData();
    }
}
