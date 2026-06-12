# GitHub Actions CI/CD Workflow Guide

## Overview

Three automated workflows have been created for your Employee Management project:

1. **build.yml** - Build & Test Pipeline
2. **code-quality.yml** - Code Quality & Security Checks
3. **deploy.yml** - Production Deployment

---

## 1️⃣ Build & Test Pipeline (build.yml)

### Triggers
- **On push to**: `main`, `develop`, `master` branches
- **On pull request to**: `main`, `develop`, `master` branches

### What It Does

| Step | Action |
|------|--------|
| 1 | ✅ Checkout code |
| 2 | ✅ Set up Java 17 |
| 3 | ✅ Display Java version |
| 4 | ✅ Clean build with Maven |
| 5 | ✅ Run all tests |
| 6 | ✅ Run code analysis (optional) |
| 7 | ✅ Generate test reports |
| 8 | ✅ Upload test results as artifacts |
| 9 | ✅ Upload coverage reports |
| 10 | ✅ Show build status |

### Jobs Matrix
- Runs on Ubuntu latest
- Uses Java 17 (from pom.xml)
- Maven caching enabled for faster builds

### Build Steps
```bash
# Clean and install dependencies
mvn clean install -DskipTests

# Run unit tests
mvn test

# Generate test reports
mvn surefire-report:report
```

### Artifacts Generated
- `test-results/` - Test execution reports
- `coverage-report/` - Code coverage reports

---

## 2️⃣ Code Quality Check (code-quality.yml)

### Triggers
- **On push to**: `main`, `develop`, `master` branches
- **On pull request to**: `main`, `develop`, `master` branches

### What It Does

| Step | Action |
|------|--------|
| 1 | ✅ Checkout code |
| 2 | ✅ Set up Java 17 |
| 3 | ✅ Check code style (Checkstyle) |
| 4 | ✅ Check security vulnerabilities (OWASP) |
| 5 | ✅ Generate code coverage (JaCoCo) |
| 6 | ✅ Upload to Codecov |

### Quality Checks

#### Checkstyle
Validates Java code against style rules
```bash
mvn checkstyle:check
```

#### Security Scanning
Checks for known vulnerabilities in dependencies
```bash
mvn org.owasp:dependency-check-maven:check
```

#### Code Coverage
Measures test coverage with JaCoCo
```bash
mvn clean test jacoco:report
```

#### Codecov Integration
Uploads coverage reports to [codecov.io](https://codecov.io)

---

## 3️⃣ Deployment Pipeline (deploy.yml)

### Triggers
- **On version tags**: When you push a tag like `v1.0.0`
- **Manual trigger**: Using GitHub UI workflow dispatch

### What It Does

| Step | Action |
|------|--------|
| 1 | ✅ Checkout code |
| 2 | ✅ Set up Java 17 |
| 3 | ✅ Build JAR file |
| 4 | ✅ Display JAR info |
| 5 | ✅ Create GitHub Release |
| 6 | ✅ Upload JAR as release asset |

### Deployment Steps

#### Build JAR
```bash
mvn clean package -DskipTests
```

#### Create Release
Automatically creates a GitHub Release with the version tag

#### Upload Artifact
The compiled JAR is attached to the release for download

---

## How to Use

### 1. Push Code to Trigger Build
```bash
git add .
git commit -m "Add new features"
git push origin main
```

**Result:** 
- ✅ Build workflow runs automatically
- ✅ Code quality checks run
- ✅ Tests execute
- ✅ Reports generated

### 2. Create Pull Request
When you create a PR to `main`, both workflows run to check:
- ✅ Code builds successfully
- ✅ All tests pass
- ✅ Code quality meets standards

### 3. Deploy to Production
```bash
# Create a version tag
git tag -a v1.0.0 -m "Release version 1.0.0"
git push origin v1.0.0
```

**Result:**
- ✅ Build & package JAR
- ✅ Create GitHub Release
- ✅ Upload JAR file
- ✅ Ready for deployment

---

## GitHub Secrets Configuration

### Required Secrets (Optional)

Add to **GitHub Repository Settings → Secrets → Actions**

#### For Codecov Integration
```
CODECOV_TOKEN = <your-codecov-token>
```

#### For SonarQube Integration
```
SONAR_TOKEN = <your-sonar-token>
SONAR_HOST = <your-sonar-url>
```

#### For Docker Registry (Optional)
```
DOCKER_USERNAME = <your-username>
DOCKER_PASSWORD = <your-password>
DOCKER_REGISTRY = docker.io
```

---

## Workflow Status Checks

### View Workflow Results

1. **In GitHub**
   - Go to **Actions** tab
   - View workflow run details
   - Check logs for each step

2. **In Pull Request**
   - Workflow status shown at bottom
   - Must pass before merging
   - Click "Details" to see full logs

3. **Branch Protection Rules**
   - Set up in **Settings → Branches**
   - Require status checks to pass
   - Prevent merging of failing builds

---

## Build Status in README

Add this badge to your README.md:

```markdown
[![Build Status](https://github.com/yourusername/Employee-Management/workflows/Build%20%26%20Test%20CI%20Pipeline/badge.svg)](https://github.com/yourusername/Employee-Management/actions)

[![Code Quality](https://github.com/yourusername/Employee-Management/workflows/Code%20Quality%20Check/badge.svg)](https://github.com/yourusername/Employee-Management/actions)

[![Deploy Status](https://github.com/yourusername/Employee-Management/workflows/Deploy%20to%20Production/badge.svg)](https://github.com/yourusername/Employee-Management/actions)
```

---

## File Structure

```
Employee-Management/
├── .github/
│   └── workflows/
│       ├── build.yml              # Build & Test
│       ├── code-quality.yml       # Code Quality
│       └── deploy.yml             # Production Deploy
├── src/
├── pom.xml
└── README.md
```

---

## Example Workflow Run

### Push to main branch
```bash
$ git push origin main
Enumerating objects: 5, done.
Counting objects: 100% (5/5), done.
Delta compression using 2 threads
To github.com:user/Employee-Management.git
   abc123..def456  main -> main
```

### GitHub Actions Automatically Triggers
```
✅ Workflow: Build & Test CI Pipeline started
   ✅ Set up JDK 17
   ✅ Build with Maven
      - Compiling Java files...
      - Running tests...
      - 45 tests passed ✅
   ✅ Generate Test Report
   ✅ Upload Artifacts
   
✅ Workflow: Code Quality Check started
   ✅ Checkstyle Check
   ✅ OWASP Security Check
   ✅ JaCoCo Coverage Report
      - Coverage: 78%
   
✅ All workflows completed successfully! 🎉
```

---

## Common Workflow Scenarios

### Scenario 1: Fix a Bug
```bash
git checkout -b fix/employee-update
# Make changes
git commit -am "Fix: Email validation in employee update"
git push origin fix/employee-update
# Create PR - Workflows run automatically
```

### Scenario 2: New Feature with Tests
```bash
git checkout -b feature/salary-increment
# Add feature + tests
git commit -am "Feature: Add salary increment endpoint"
git push origin feature/salary-increment
# Create PR - All workflows must pass before merge
```

### Scenario 3: Release Version
```bash
git tag v2.0.0
git push origin v2.0.0
# Deploy workflow runs automatically
# JAR available in GitHub Releases
```

---

## Troubleshooting

### Build Fails
1. Check the build.yml logs
2. Look for compilation errors
3. Verify all dependencies in pom.xml
4. Check Java version compatibility

### Tests Fail
1. Review test failures in logs
2. Debug locally with: `mvn test`
3. Fix failing tests
4. Push again

### Code Quality Issues
1. Review code-quality.yml results
2. Fix style issues with Checkstyle
3. Review security vulnerabilities
4. Increase test coverage

### Deployment Fails
1. Ensure tag format is correct (`v1.0.0`)
2. Check deploy.yml logs
3. Verify GitHub token permissions
4. Retry manually from Actions tab

---

## Next Steps

1. **Push your code** to GitHub repository
2. **Commit these workflow files**
   ```bash
   git add .github/workflows/
   git commit -m "Add GitHub Actions CI/CD workflows"
   git push origin main
   ```

3. **View your workflows**
   - Go to repository **Actions** tab
   - Watch them run automatically

4. **Configure Branch Protection** (Optional)
   - Settings → Branches → Protect main branch
   - Require status checks pass before merge

5. **Add badges** to README.md

---

## Resources

- [GitHub Actions Documentation](https://docs.github.com/en/actions)
- [Maven on GitHub Actions](https://github.com/marketplace/actions/setup-java-jdk)
- [Codecov Integration](https://codecov.io/gh)
- [JaCoCo Maven Plugin](https://www.jacoco.org/jacoco/trunk/doc/maven.html)
- [Checkstyle Maven Plugin](https://maven.apache.org/plugins/maven-checkstyle-plugin/)

---

## Summary

Your Employee Management project now has:

✅ **Automated Build Pipeline** - Builds on every push
✅ **Automated Tests** - Runs unit tests automatically
✅ **Code Quality Checks** - Security & style validation
✅ **Code Coverage Reports** - Tracks test coverage
✅ **Production Deployment** - Tag-based releases
✅ **Artifact Management** - JAR files in GitHub Releases

Just push your code and let GitHub Actions handle the rest! 🚀

