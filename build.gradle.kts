plugins {
    id("java-library")
    id("maven-publish")
    id("idea")
}

group = "org.indilib.i4j"
description = "INDIForJava-core"
version = "2.0.3"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    api("org.slf4j:slf4j-api:1.7.32")
    api("org.glassfish.tyrus:tyrus-client:2.0.1")
    implementation("com.thoughtworks.xstream:xstream:1.4.18")
}

tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        from("META-INF/MANIFEST.MF")
    }
}

tasks {
    val sourcesJar by creating(Jar::class) {
        archiveClassifier.set("sources")
        from(sourceSets.main.get().allSource)
    }

    val javadocJar by creating(Jar::class) {
        dependsOn.add(javadoc)
        archiveClassifier.set("javadoc")
        from(javadoc)
    }

    artifacts {
        archives(sourcesJar)
        archives(javadocJar)
        archives(jar)
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            pom {
                name.set(project.name)
                description.set("INDIForJava is a set of libraries to implement clients and servers that follow the INDI protocol, designed to operate astronomical instrumentation.")
                url.set("https://github.com/INDIForJava/INDIForJava-core")
                licenses {
                    license {
                        name.set("GNU Lesser General Public License")
                        url.set("https://www.gnu.org/licenses/lgpl-3.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("marcocipriani01")
                        name.set("Marco Cipriani")
                        email.set("marco.cipriani.01@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/INDIForJava/INDIForJava-core.git")
                    developerConnection.set("scm:git:ssh://github.com/INDIForJava/INDIForJava-core.git")
                    url.set("https://github.com/INDIForJava/INDIForJava-core")
                }
            }
        }
    }
}