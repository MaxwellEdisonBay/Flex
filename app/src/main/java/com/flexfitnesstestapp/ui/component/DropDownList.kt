package com.flexfitnesstestapp.ui.component

//@Composable
//fun DropDownList(
//    requestToOpen: Boolean = false,
//    list: List<ProductTypesEnum>,
//    request: (Boolean) -> Unit,
//    selectedString: (ProductTypesEnum) -> Unit
//) {
//    DropdownMenu(
//
//        modifier = Modifier
//            .fillMaxWidth(0.8f)
//            .border(BorderStroke(0.dp, Color.Transparent), shape = RoundedCornerShape(12.dp)),
//        expanded = requestToOpen,
//        onDismissRequest = { request(false) },
//    ) {
//        list.forEach {
//            DropdownMenuItem(
//                text = { Text(
//                    stringResource(id = it.text),
//                    modifier = Modifier
//                        .wrapContentWidth()
//                        .align(Alignment.Start))
//                },
//                modifier = Modifier.fillMaxWidth(),
//                onClick = {
//                    request(false)
//                    selectedString(it)
//                }
//            )
//        }
//    }
//}