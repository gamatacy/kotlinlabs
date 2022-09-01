package productClasses

import console.User
import enums.UnitOfMeasure
import java.util.*
import javax.persistence.*


@Entity
@Table(name="products")
open class ProductEntity(
    @Id
    open var id: Int? = null,

    open var name: String? = null,

    open var x: Float? = null,

    open var y: Float?  = null,

    @Column(name = "creation_date")
    open var creationDate: Date? = null,

    open var price: Float?  = null,

    @Column(name = "part_number")
    open var partNumber: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "unit_of_measure")
    open var unitOfMeasure: UnitOfMeasure? = null,

    @Column(name = "manufacture_cost")
    open var manufactureCost: Int? = null,

    @Column(name="manufacturer")
    open var manufacturerId: Int? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    open var owner: User? = null

)
{

}