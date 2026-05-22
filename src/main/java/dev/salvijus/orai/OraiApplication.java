package dev.salvijus.orai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@Controller
public class OraiApplication {
    static void main(String[] args) {
        SpringApplication.run(OraiApplication.class, args);
    }

    // 2. Initial Page Route: Serves the main template when navigating to http://localhost:8080/
    @GetMapping("/")
    public String showMainPage(Model model) {
        model.addAttribute("message", "Hello World from a single Java file backend!");
        return "hello"; // Looks for src/main/jte/hello.jte
    }

    // 3. HTMX AJAX Route: Responds to interactive frontend clicks without reloading the whole page
    @PostMapping("/time")
    @ResponseBody // Tells Spring to return raw text/HTML fragments instead of looking for a separate .jte file
    public String getSystemTime(@RequestParam(value = "username", defaultValue = "Developer") String name) {
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a"));

        // Returning a raw Tailwind-styled HTML string fragment directly to HTMX
        return """
            <div class="mt-4 p-4 bg-indigo-50 border border-indigo-100 rounded-xl animate-fade-in">
                <p class="text-sm text-indigo-900 font-medium">
                    Hey <span class="font-bold text-indigo-700">%s</span>, the server time is:
                </p>
                <p class="text-2xl font-mono font-black text-indigo-600 mt-1">
                    %s
                </p>
            </div>
            """.formatted(name, currentTime);
    }
}