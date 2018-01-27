package com.mystic.model.maping;

/**
 * @author Pavel Putrenkov
 * @version 1.0
 * @since
 */
public class FromTableToTableDtoConverter {
//    @Override
//    protected TableDto convert(Table table) {
//        if (Objects.nonNull(table)) {
//            TableDto tableDto = new TableDto();
//            tableDto.setId(table.getId());
//            tableDto.setTableDescription(table.getTableDescription());
//            tableDto.setTableName(table.getTableName());
//            tableDto.setTableNameInDatabase(table.getTableNameInDatabase());
//            tableDto.setWidth(table.getWidth());
//            Set<FieldDto> fieldDtoSet = extractSetOfFieldDtoFromSetOfField(table.getFields());
//            tableDto.setFields(fieldDtoSet);
//            return tableDto;
//        }
//        return new TableDto();
//    }
//
//    private Set<FieldDto> extractSetOfFieldDtoFromSetOfField(Set<Field> setOfField) {
//        Set<FieldDto> setOfFieldDto = new LinkedHashSet<>();
//        setOfField.stream().sorted(Comparator.comparing(Field::getId)).forEach(field -> setOfFieldDto.add(buildFieldDtoFromField(field)));
//        return setOfFieldDto;
//    }
//
//    private FieldDto buildFieldDtoFromField(Field field) {
//        if (Objects.nonNull(field)) {
//            FieldDto fieldDto = new FieldDto();
//            fieldDto.setId(field.getId());
//            fieldDto.setFieldName(field.getFieldName());
//            fieldDto.setFieldNameInDatabase(field.getFieldNameInDatabase());
//            fieldDto.setTableName(field.getTableName());
//            fieldDto.setFieldDescription(field.getFieldDescription());
//            return fieldDto;
//        }
//        return new FieldDto();
//    }
}
