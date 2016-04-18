# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('expense_sharing', '0007_deleteaction_billtype'),
    ]

    operations = [
        migrations.AlterField(
            model_name='deleteaction',
            name='billtype',
            field=models.CharField(default=b'manual', max_length=128, null=True),
        ),
        migrations.AlterField(
            model_name='expenses',
            name='billtype',
            field=models.CharField(default=b'manual', max_length=128, null=True),
        ),
    ]
