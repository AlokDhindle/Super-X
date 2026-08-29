
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



public interface CreateUserMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      CreateUserMutation.Data,
      Unit
    >
{
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val user_insert: UserKey,
  
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "CreateUser"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Unit> =
      kotlinx.serialization.serializer()
  }
}

public fun CreateUserMutation.ref(
  
): com.google.firebase.dataconnect.MutationRef<
    CreateUserMutation.Data,
    Unit
  > =
  ref(
    
      Unit
    
  )

public suspend fun CreateUserMutation.execute(

  

  ): com.google.firebase.dataconnect.MutationResult<
    CreateUserMutation.Data,
    Unit
  > =
  ref(
    
  ).execute()


