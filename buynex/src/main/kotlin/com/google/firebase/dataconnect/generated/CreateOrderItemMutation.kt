
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



public interface CreateOrderItemMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      CreateOrderItemMutation.Data,
      CreateOrderItemMutation.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val orderId: @kotlinx.serialization.Serializable(with = com.google.firebase.dataconnect.serializers.UUIDSerializer::class) java.util.UUID,
  
    val productId: @kotlinx.serialization.Serializable(with = com.google.firebase.dataconnect.serializers.UUIDSerializer::class) java.util.UUID,
  
  ) {
    
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val orderItem_insert: OrderItemKey,
  
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "CreateOrderItem"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun CreateOrderItemMutation.ref(
  
    orderId: java.util.UUID,productId: java.util.UUID,

  
  
): com.google.firebase.dataconnect.MutationRef<
    CreateOrderItemMutation.Data,
    CreateOrderItemMutation.Variables
  > =
  ref(
    
      CreateOrderItemMutation.Variables(
        orderId=orderId,productId=productId,
  
      )
    
  )

public suspend fun CreateOrderItemMutation.execute(

  
    
      orderId: java.util.UUID,productId: java.util.UUID,

  

  ): com.google.firebase.dataconnect.MutationResult<
    CreateOrderItemMutation.Data,
    CreateOrderItemMutation.Variables
  > =
  ref(
    
      orderId=orderId,productId=productId,
  
    
  ).execute()


