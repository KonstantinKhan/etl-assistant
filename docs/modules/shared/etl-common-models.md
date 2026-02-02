# ETL Common Models

This module describes the domain models to be used in the application.

## Location

### Module

[etl-common-models](../../../shared/etl-common-models)

### Source code

[src](../../../shared/etl-common-models/src/commonMain/kotlin/com/khan366kos/common)

## Module Structure

### Folder Structure with Files

```
shared/etl-common-models/
├── build.gradle.kts
├── build/
└── src/
    └── commonMain/
        └── kotlin/
            └── com/
                └── khan366kos/
                    └── common/
                        ├── excel/
                        │   └── models/
                        │       ├── simple/
                        │       │   ├── EtlSheetTitle.kt
                        │       │   └── EtlTableHeader.kt
                        │       ├── EtlSheet.kt
                        │       └── EtlWorkbook.kt
                        ├── models/
                        │   ├── business/
                        │   │   ├── Identifier.kt
                        │   │   ├── InnerElement.kt
                        │   │   ├── ObjectInfo.kt
                        │   │   ├── OwnerGroup.kt
                        │   │   └── PathElement.kt
                        │   ├── classifier/
                        │   │   ├── ClassifierGroup.kt
                        │   │   └── RawClassifierGroup.kt
                        │   ├── contracts/
                        │   │   └── Contract.kt
                        │   ├── definitions/
                        │   │   └── DefinitionModels.kt
                        │   ├── items/
                        │   │   └── ItemModels.kt
                        │   ├── measure/
                        │   │   ├── MeasureEntityModel.kt
                        │   │   └── MeasureUnitModel.kt
                        │   ├── simple/
                        │   │   ├── Applicability.kt
                        │   │   ├── ElementName.kt
                        │   │   ├── GroupId.kt
                        │   │   ├── IconCode.kt
                        │   │   ├── IconColor.kt
                        │   │   ├── ObjectId.kt
                        │   │   ├── PathId.kt
                        │   │   └── TypeId.kt
                        │   └── values/
                        │       └── ValueModels.kt
                        ├── repo/
                        │   └── ClassifierGroupRepository.kt
                        ├── requests/
                        │   ├── CreateElementRequest.kt
                        │   ├── ElementDeleteRequest.kt
                        │   ├── IdRequest.kt
                        │   ├── ParentGroup.kt
                        │   ├── PropertyAssignmentRequest.kt
                        │   ├── PropertyOwnerRequest.kt
                        │   └── PropertyValueAssignment.kt
                        └── responses/
                            ├── ElementResponse.kt
                            └── PropertyOwnerRespose.kt
```

