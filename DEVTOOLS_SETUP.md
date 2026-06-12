# DevTools Setup & Troubleshooting Guide

## ✅ DevTools is Already in pom.xml

Your `pom.xml` already includes:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```

## ❌ Why DevTools Might Not Be Working

The most common reason is that your **IDE's Auto-Compile** is not enabled!

---

## ✅ SOLUTION: Enable Auto-Compile in IntelliJ IDEA

### Step 1: Enable Auto-Build
1. Go to **File → Settings → Build, Execution, Deployment → Compiler**
2. Check: ☑️ **"Build project automatically"**
3. Click **Apply** → **OK**

### Step 2: Enable Auto-Make in Running App
1. Go to **File → Settings → Build, Execution, Deployment → Debugger**
2. Under "On frame deactivation", select:
   - **"Make project"** (builds when you focus away from IDE)
   - Or use keyboard shortcut: **Ctrl + F9** (manual build)

### Step 3: Configure DevTools Restart
1. Go to **File → Settings → Advanced Settings**
2. Check: ☑️ **"Allow auto-make in background while app is running"**
3. Click **Apply** → **OK**

### Step 4: Restart IDE
Close and reopen IntelliJ IDEA completely.

---

## 🚀 How to Test DevTools

### Step 1: Start Application
```bash
mvn spring-boot:run
```
Or run from IntelliJ's Run button.

### Step 2: Test with curl in another terminal
```bash
curl http://localhost:8090/employees
```

### Step 3: Make a Change
Open `EmployeeController.java` and change the log message:
```java
// BEFORE
log.info("GET request to retrieve all employees");

// AFTER
log.info("🔥 FETCHING ALL EMPLOYEES NOW!");
```

### Step 4: Save File
- Press **Ctrl + S** to save
- Watch IntelliJ console - you should see:
```
[main] INFO  o.s.b.d.a.RestartApplicationListener : 
Restarting Spring Application Context...

After a few seconds...

[main] INFO  o.s.b.w.e.t.TomcatWebServer : 
Tomcat started on port(s): 8090
```

### Step 5: Test Again
```bash
curl http://localhost:8090/employees
```
The log message should show your updated message! ✅

---

## 🔧 DevTools Configuration Properties

Added to your `application.properties`:

```properties
# Enable/Disable DevTools restart
spring.devtools.restart.enabled=true

# Additional paths to monitor for changes
spring.devtools.restart.additional-paths=src/main/java

# Exclude certain paths from restart
spring.devtools.restart.additional-exclude=static/**,public/**

# Enable live reload for static files
spring.devtools.livereload.enabled=true

# Polling interval (in milliseconds)
spring.devtools.restart.poll-interval=2000

# Quiet period before restart (in milliseconds)
spring.devtools.restart.quiet-period=1000
```

---

## ✅ Complete DevTools Setup Checklist

- [ ] DevTools dependency in pom.xml ✓ (Already there)
- [ ] DevTools properties in application.properties ✓ (Added)
- [ ] IntelliJ "Build project automatically" enabled
- [ ] IntelliJ "Make project" on frame deactivation enabled
- [ ] IDE restarted after settings change
- [ ] Application running with `mvn spring-boot:run`
- [ ] Verified with test endpoint curl request

---

## 🐛 Debugging: Verify DevTools is Active

Add this method to your EmployeeController:

```java
@GetMapping("/health")
public ResponseEntity<String> health() {
    return ResponseEntity.ok("DevTools is active! " + LocalDateTime.now());
}
```

Then:
1. Start app
2. Test: `curl http://localhost:8090/employees/health`
3. Change the message
4. Save file (Ctrl + S)
5. Wait ~3 seconds for auto-restart
6. Test again: `curl http://localhost:8090/employees/health`

If the message updates without you manually restarting - **DevTools is working!** 🎉

---

## 💡 Pro Tips

### Keyboard Shortcuts
- **Ctrl + S**: Save (triggers compile)
- **Ctrl + F9**: Manual build (forces restart)
- **Shift + Ctrl + F10**: Re-run application

### Console Output to Confirm Restart
Look for these lines in console:
```
Restarting Spring Application Context...
DevTools have started, the application will be automatically restarted on file changes.
```

### Live Reload Browser Extension (Optional)
Install Live Reload extension to auto-refresh browser:
- Chrome: [Live.js](https://chrome.google.com/webstore)
- Firefox: [Live.js](https://addons.mozilla.org/firefox)

---

## ❓ Still Not Working?

Try these steps:
1. **Clean Maven Cache**: `mvn clean install`
2. **Invalidate IDE Cache**: File → Invalidate Caches → Restart
3. **Check Java Compiler**: Settings → Build, Execution, Deployment → Java Compiler
4. **Verify Maven Home**: Settings → Build, Execution, Deployment → Build Tools → Maven
5. **Delete target folder**: `rm -r target/` then rebuild

---

## 📝 Summary

Your DevTools is now:
✅ Configured in pom.xml
✅ Configured in application.properties
✅ Ready to use with IDE auto-compile enabled

**Just enable IDE auto-compile and restart IntelliJ!** 🚀

