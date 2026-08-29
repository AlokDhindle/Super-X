
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



public interface CreateOrderMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      CreateOrderMutation.Data,
      CreateOrderMutation.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val storeId: @kotlinx.serialization.Serializable(with = com.google.firebase.dataconnect.serializers.UUIDSerializer::class) java.util.UUID,
  
  ) {
    
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val order_insert: OrderKey,
  
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "CreateOrder"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun CreateOrderMutation.ref(
  
    storeId: java.util.UUID,

  
  
): com.google.firebase.dataconnect.MutationRef<
    CreateOrderMutation.Data,
    CreateOrderMutation.Variables
  > =
  ref(
    
      CreateOrderMutation.Variables(
        storeId=storeId,
  
      )
    
  )

public suspend fun CreateOrderMutation.execute(

  
    
      storeId: java.util.UUID,

  

  ): com.google.firebase.dataconnect.MutationResult<
    CreateOrderMutation.Data,
    CreateOrderMutation.Variables
  > =
  ref(
    
      storeId=storeId,
  
    
  ).execute()


