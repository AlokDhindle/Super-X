
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



public interface CreateDeliveryMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      CreateDeliveryMutation.Data,
      CreateDeliveryMutation.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val orderId: @kotlinx.serialization.Serializable(with = com.google.firebase.dataconnect.serializers.UUIDSerializer::class) java.util.UUID,
  
  ) {
    
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val delivery_insert: DeliveryKey,
  
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "CreateDelivery"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun CreateDeliveryMutation.ref(
  
    orderId: java.util.UUID,

  
  
): com.google.firebase.dataconnect.MutationRef<
    CreateDeliveryMutation.Data,
    CreateDeliveryMutation.Variables
  > =
  ref(
    
      CreateDeliveryMutation.Variables(
        orderId=orderId,
  
      )
    
  )

public suspend fun CreateDeliveryMutation.execute(

  
    
      orderId: java.util.UUID,

  

  ): com.google.firebase.dataconnect.MutationResult<
    CreateDeliveryMutation.Data,
    CreateDeliveryMutation.Variables
  > =
  ref(
    
      orderId=orderId,
  
    
  ).execute()


