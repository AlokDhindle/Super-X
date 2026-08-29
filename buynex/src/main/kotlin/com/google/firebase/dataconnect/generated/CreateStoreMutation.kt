
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



public interface CreateStoreMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      CreateStoreMutation.Data,
      Unit
    >
{
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val store_insert: StoreKey,
  
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "CreateStore"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Unit> =
      kotlinx.serialization.serializer()
  }
}

public fun CreateStoreMutation.ref(
  
): com.google.firebase.dataconnect.MutationRef<
    CreateStoreMutation.Data,
    Unit
  > =
  ref(
    
      Unit
    
  )

public suspend fun CreateStoreMutation.execute(

  

  ): com.google.firebase.dataconnect.MutationResult<
    CreateStoreMutation.Data,
    Unit
  > =
  ref(
    
  ).execute()


