package com.xl1.awpm;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.xl1.awpm");

        noClasses()
            .that()
            .resideInAnyPackage("com.xl1.awpm.service..")
            .or()
            .resideInAnyPackage("com.xl1.awpm.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.xl1.awpm.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
