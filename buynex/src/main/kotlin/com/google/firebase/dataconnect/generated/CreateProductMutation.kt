
@file:Suppress(
  "KotlinRedundantDiagnosticSuppress",
  "PropertyName",
  "MayBeConstant",
  "RedundantVisibilityModifier",
  "RedundantCompanionReference",
  "RemoveEmptyClassBody",
  "SpellCheckingInspection",
  "unused",
)

package com.google.firebase.dataconnect.generated



public interface CreateProductMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      CreateProductMutation.Data,
      CreateProductMutation.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val storeId: @kotlinx.serialization.Serializable(with = com.google.firebase.dataconnect.serializers.UUIDSerializer::class) java.util.UUID,
  
  ) {
    
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val product_insert: ProductKey,
  
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "CreateProduct"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun CreateProductMutation.ref(
  
    storeId: java.util.UUID,

  
  
): com.google.firebase.dataconnect.MutationRef<
    CreateProductMutation.Data,
    CreateProductMutation.Variables
  > =
  ref(
    
      CreateProductMutation.Variables(
        storeId=storeId,
  
      )
    
  )

public suspend fun CreateProductMutation.execute(

  
    
      storeId: java.util.UUID,

  

  ): com.google.firebase.dataconnect.MutationResult<
    CreateProductMutation.Data,
    CreateProductMutation.Variables
  > =
  ref(
    
      storeId=storeId,
  
    
  ).execute()


