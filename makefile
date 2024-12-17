# Gradle
GRADLE := gradle/wrapper/gradle-wrapper.jar

# PHONY target
.PHONY: clean test build

# Build app
build: $(GRADLE)
	mkdir -p ready
	./gradlew shadowJar --no-daemon

# Test app
test: $(GRADLE)
	./gradlew test --no-daemon

# Install Gradle
$(GRADLE):
	curl https://services.gradle.org/distributions/gradle-8.8-bin.zip -LSso gradle-8.8-bin.zip
	unzip gradle-8.8-bin.zip
	unzip -d tmp gradle-8.8/lib/plugins/gradle-wrapper-8.8.jar
	cp tmp/gradle-wrapper.jar $(GRADLE)
	rm -rf tmp gradle-8.8 gradle-8.8-bin.zip

# Clean
clean:
	rm -rf build